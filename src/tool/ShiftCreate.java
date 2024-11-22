package tool;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import bean.Shift;
import bean.Store;
import dao.StoreDao;

public class ShiftCreate {

    // 必要人数を表すクラス
    static class ShiftRequirement {
        String startTime;  // 開始時刻
        String endTime;    // 終了時刻
        int requiredKitchen; // 必要なキッチンスタッフ
        int requiredHall;    // 必要なホールスタッフ

        ShiftRequirement(String startTime, String endTime, int requiredKitchen, int requiredHall) {
            this.startTime = startTime;
            this.endTime = endTime;
            this.requiredKitchen = requiredKitchen;
            this.requiredHall = requiredHall;
        }
    }

    // バイトの情報を表すクラス
    public static class WorkerShift {
        String name;         // バイトの名前
        String role;         // 役割（キッチン or ホール）
        String availableFrom; // 勤務可能開始時刻
        String availableTo;   // 勤務可能終了時刻
        int maxHours;         // 最大勤務時間
        List<String> assignedShifts = new ArrayList<>(); // 割り振られたシフト
        List<String> mergedShifts = new ArrayList<>();

        WorkerShift(String name, String role, String availableFrom, String availableTo, int maxHours) {
            this.name = name;
            this.role = role;
            this.availableFrom = availableFrom;
            this.availableTo = availableTo;
            this.maxHours = maxHours;
        }
    }

    // 時間の文字列を分単位に変換
    private static int timeToMinutes(String time) {
        String[] parts = time.split(":");
        return Integer.parseInt(parts[0]) * 60 + Integer.parseInt(parts[1]);
    }

    // 分単位の時間を文字列に変換
    private static String minutesToTime(int minutes) {
        int hours = minutes / 60;
        int mins = minutes % 60;
        return String.format("%02d:%02d", hours, mins);
    }

    // 分単位の時間を時間に変換に変換
    private static int minutesToHour(int minutes) {
        int hours = minutes / 60;
        return hours;
    }

 // 勤務時間帯を連続して統合
    private static List<String> mergeShifts(List<String> shifts) {
        List<String> merged = new ArrayList<>();
        if (shifts.isEmpty()) return merged;

        String[] current = shifts.get(0).split("-");
        int start = timeToMinutes(current[0]);
        int end = timeToMinutes(current[1]);

        for (int i = 1; i < shifts.size(); i++) {
            String[] next = shifts.get(i).split("-");
            int nextStart = timeToMinutes(next[0]);
            int nextEnd = timeToMinutes(next[1]);

            if (nextStart == end) {
                end = nextEnd; // 連続する場合、終了時間を延長
            } else {
                merged.add(minutesToTime(start) + "-" + minutesToTime(end));
                start = nextStart;
                end = nextEnd;
            }
        }
        merged.add(minutesToTime(start) + "-" + minutesToTime(end)); // 最後のシフトを追加
        return merged;
    }



    public static List<Map<String, Object>> getWorkerMergedShifts(List<WorkerShift> workers) {
        List<Map<String, Object>> result = new ArrayList<>();

        for (WorkerShift worker : workers) {
            Map<String, Object> workerInfo = new HashMap<>();
            workerInfo.put("name", worker.name);
            workerInfo.put("role", worker.role);
            workerInfo.put("mergedShifts", worker.mergedShifts);
            result.add(workerInfo);
        }

        return result;
    }


    public static Map<String, List<Map<String, String>>> createShiftSchedule(
            ShiftRequirement requirement, List<WorkerShift> workers) {

        Map<String, List<Map<String, String>>> schedule = new TreeMap<>();

        int startMinutes = timeToMinutes(requirement.startTime);
        int endMinutes = timeToMinutes(requirement.endTime);

        for (int current = startMinutes; current < endMinutes; current += 60) {
            String timeSlot = minutesToTime(current) + "-" + minutesToTime(current + 60);
            List<Map<String, String>> assignedWorkers = new ArrayList<>();

            int kitchenNeeded = requirement.requiredKitchen;
            int hallNeeded = requirement.requiredHall;

            for (WorkerShift worker : workers) {
                int workerStart = timeToMinutes(worker.availableFrom);
                int workerEnd = timeToMinutes(worker.availableTo);

                if (worker.maxHours > 0 &&
                        current >= workerStart &&
                        current + 60 <= workerEnd) {

                    if (worker.role.equals("Kitchen") && kitchenNeeded > 0) {
                        Map<String, String> assignment = new HashMap<>();
                        assignment.put("name", worker.name);
                        assignment.put("role", worker.role);
                        assignedWorkers.add(assignment);
                        worker.assignedShifts.add(timeSlot);
                        worker.maxHours--;
                        kitchenNeeded--;
                    } else if (worker.role.equals("Hall") && hallNeeded > 0) {
                        Map<String, String> assignment = new HashMap<>();
                        assignment.put("name", worker.name);
                        assignment.put("role", worker.role);
                        assignedWorkers.add(assignment);
                        worker.assignedShifts.add(timeSlot);
                        worker.maxHours--;
                        hallNeeded--;
                    }
                }

                if (kitchenNeeded == 0 && hallNeeded == 0) break;
            }

            schedule.put(timeSlot, assignedWorkers);
        }

        for (WorkerShift worker : workers) {
            worker.mergedShifts = mergeShifts(worker.assignedShifts);
        }

        return schedule;
    }




    public List<Map<String,Object>> Shiftmain(String work_time_start, String work_time_end, Shift shift,Store shift_manager, List<Shift> shift_list) {
        // 必要人数の設定（キッチンとホールで区別）
        ShiftRequirement requirement = new ShiftRequirement(work_time_start, work_time_end, 1, 1);
        //シフトの情報を格納する
        List<Map<String, Object>> ShiftDetail = new ArrayList<>();
        List<WorkerShift> workers = new ArrayList<>();
        StoreDao stDao = new StoreDao();//StoreDao初期化


        try{
        	// バイト情報（役割も含む）
        	for(Shift shift_lists: shift_list){
        		System.out.println(shift_lists.getShiftHopeTimeId());
        		System.out.println(shift_lists.getWorker().getWorkerId());

        		//シフト希望時間ID（A,B,C,D）がある場合
        		if(shift_lists.getShiftHopeTimeId() !=null) {
        			String availableFrom = null;
        			String availableTo	= null;
        			int maxHours = 0;
        			Store work_time = new Store();
        			work_time = stDao.Time_get(shift_manager.getStoreId(), shift_lists.getShiftHopeTimeId());
        			availableFrom = work_time.getWorkTimeStart().toString();
        			availableTo   = work_time.getWorkTimeEnd().toString();
        			maxHours =minutesToHour(timeToMinutes(availableTo)-timeToMinutes(availableFrom));
        			workers.add(new WorkerShift(shift_lists.getWorker().getWorkerName(), "Kitchen", availableFrom, availableTo, maxHours));


        			//シフト希望時間がその他の場合
        		}else{
        			String availableFrom = null;
        			String availableTo	= null;
        			int maxHours = 0;
        			LocalTime timeFrom =shift_lists.getShiftHopeTimeStart().toLocalDateTime().toLocalTime();
        			LocalTime timeTo =shift_lists.getShiftHopeTimeEnd().toLocalDateTime().toLocalTime();
        			availableFrom = timeFrom.toString();
        			availableTo   = timeTo.toString();
        			maxHours =minutesToHour(timeToMinutes(availableTo)-timeToMinutes(availableFrom));
        			workers.add(new WorkerShift(shift_lists.getWorker().getWorkerName(), "Hall", availableFrom, availableTo, maxHours));
        		}
			}
        }catch (Exception e) {
        	System.out.println(e);
		}

        createShiftSchedule(requirement, workers);

        // 各人の統合された勤務時間を取得
        List<Map<String, Object>> workerMergedShifts = getWorkerMergedShifts(workers);

        System.out.println("\n=== 各人の勤務時間 ===");
        for (Map<String, Object> workerInfo : workerMergedShifts) {
            System.out.println("Name: " + workerInfo.get("name"));
            System.out.println("Role: " + workerInfo.get("role"));
            System.out.println("Shifts: " + workerInfo.get("mergedShifts"));
            System.out.println();
        }



        return workerMergedShifts;
    }
}
