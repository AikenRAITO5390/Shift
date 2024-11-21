package tool;

import java.time.LocalTime;
import java.util.ArrayList;
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


    public List<Map<String,Object>> Shiftmain(String work_time_start, String work_time_end, Shift shift,Store shift_manager, List<Shift> shift_list) {
        // 必要人数の設定（キッチンとホールで区別）
        ShiftRequirement requirement = new ShiftRequirement(work_time_start, work_time_end, 1, 1);
        //シフトの情報を格納する
        List<Map<String, Object>> ShiftDetail = new ArrayList<>();
        List<WorkerShift> workers = new ArrayList<>();
        StoreDao stDao = new StoreDao();//StoreDao初期化

        // 社員（固定勤務、常にシフトに含まれる）
        String manager = "Manager";


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

        // シフトスケジュールを格納するマップ（時間帯 → 割り振りリスト）時系列で格納される
        Map<String, List<String>> schedule = new TreeMap<>();

        // シフトを区切る時間単位（1時間単位）
        int interval = 60; // 分単位の間隔
        int startMinutes = timeToMinutes(requirement.startTime); // 開始時刻を分に変換
        int endMinutes = timeToMinutes(requirement.endTime);     // 終了時刻を分に変換

        // シフト作成ループ 1時間区切りでシフトを作成
        for (int current = startMinutes; current < endMinutes; current += interval) {
            String timeSlot = minutesToTime(current) + "-" + minutesToTime(current + interval); // 時間帯文字列
            List<String> assigned = new ArrayList<>(); // 割り振られる従業員リスト
            assigned.add(manager); // 社員を追加（常に勤務）

            int requiredKitchen = requirement.requiredKitchen; // 必要なキッチンスタッフ数
            int requiredHall = requirement.requiredHall;       // 必要なホールスタッフ数

            // 各バイトを確認
            for (WorkerShift worker : workers) {
                int workerStart = timeToMinutes(worker.availableFrom); // 勤務可能開始時刻
                int workerEnd = timeToMinutes(worker.availableTo);     // 勤務可能終了時刻

                // 勤務条件を満たし、まだ必要人数が残っている場合
                if (current >= workerStart && current + interval <= workerEnd && worker.maxHours > 0) {
                    if (worker.role.equals("Kitchen") && requiredKitchen > 0) {
                        assigned.add(worker.name + " (Kitchen)");      // バイトを割り当て
                        worker.assignedShifts.add(timeSlot);          // シフト記録
                        worker.maxHours -= interval / 60;            // 勤務可能時間を減らす
                        requiredKitchen--;                           // 必要キッチン人数を減らす
                    } else if (worker.role.equals("Hall") && requiredHall > 0) {
                        assigned.add(worker.name + " (Hall)");        // バイトを割り当て
                        worker.assignedShifts.add(timeSlot);          // シフト記録
                        worker.maxHours -= interval / 60;            // 勤務可能時間を減らす
                        requiredHall--;                              // 必要ホール人数を減らす
                    }
                }
            }
            schedule.put(timeSlot, assigned); // スケジュールに現在の時間帯を追加
        }

        // シフトスケジュールの表示
        System.out.println("=== シフトスケジュール ===");
        for (Map.Entry<String, List<String>> entry : schedule.entrySet()) {
            System.out.println("Time Slot: " + entry.getKey()); // 時間帯を表示
            System.out.println("  Assigned: " + entry.getValue()); // 割り振られた従業員
        }

        // 各人の割り振られたシフトを表示
        System.out.println("\n=== 各人の勤務時間 ===");
        for (WorkerShift worker : workers) {
            if (!worker.assignedShifts.isEmpty()) {
                List<String> mergedShifts = mergeShifts(worker.assignedShifts);
                System.out.println(worker.name + " (" + worker.role + "): " + mergedShifts);
            }
        }


        // 社員の勤務時間を表示
        System.out.println(manager + ": [" + requirement.startTime + "-" + requirement.endTime + "]");

        return ShiftDetail;
    }
}
