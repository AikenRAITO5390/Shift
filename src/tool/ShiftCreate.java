package tool;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.TreeMap;

import bean.Shift;
import bean.Store;
import bean.Worker;
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
        int priority;       // 優先度
        boolean judge;		//社員判別
        int power;          //バイトパワー

        public void setPriority(int priority) {
			this.priority = priority;
		}

		List<String> assignedShifts = new ArrayList<>(); // 割り振られたシフト
        List<String> mergedShifts = new ArrayList<>();//開始と終了時間が入るリスト

        WorkerShift(String name, String role, String availableFrom, String availableTo, int maxHours, boolean judge,int power) {
            this.name = name;
            this.role = role;
            this.availableFrom = availableFrom;
            this.availableTo = availableTo;
            this.maxHours = maxHours;
            this.judge	= judge;
            this.power = power;
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
    private static List<String> mergeShifts(List<String> shifts,Store shift_manager,WorkerShift worker) {
        List<String> merged = new ArrayList<>();

        if (shifts.isEmpty()) return merged;

        if(worker.judge){
        	merged.add("T");
        	return merged;
        }
        StoreDao stDao = new StoreDao();
        List<String>  store_time_id = Arrays.asList("A","B","C","D");
        //currentに開始時間を格納
        String[] current = shifts.get(0).split("-");
        int start = timeToMinutes(current[0]);
        int end = timeToMinutes(current[1]);

        //シフトに入っている分回る
        for (int i = 1; i < shifts.size(); i++) {
            String[] next = shifts.get(i).split("-");
            int nextStart = timeToMinutes(next[0]);
            int nextEnd = timeToMinutes(next[1]);
            if (nextStart == end) {
                end = nextEnd;
            } else {
            	// 連続する場合、終了時間を延長
                merged.add(minutesToTime(start) + "-" + minutesToTime(end));
                start = nextStart;
                end = nextEnd;
            }
        }

        for(String time_id:store_time_id){
        	try {
        		Store Time = new Store();
				Time = stDao.Time_get(shift_manager.getStoreId(), time_id);
				String time_id_start = Time.getWorkTimeStart().toString();
				String time_id_end = Time.getWorkTimeEnd().toString();
				int time_start =timeToMinutes(time_id_start);
				int time_end =timeToMinutes(time_id_end);
				if(time_start == start && time_end == end){
					merged.add(time_id);
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
        }
        if(merged.isEmpty()){
        	merged.add(minutesToTime(start) + "-" + minutesToTime(end)); // 最後のシフトを追加
        }
        return merged;
    }



    public static List<Map<String, Object>> getWorkerMergedShifts(List<WorkerShift> workers, String date) {
        List<Map<String, Object>> result = new ArrayList<>();
        //日にち、名前、役割、シフトを格納する
        for (WorkerShift worker : workers) {
        	if(worker.judge){
        		Map<String, Object> workerInfo = new HashMap<>();
                workerInfo.put("date",date );
                workerInfo.put("name", worker.name);
                workerInfo.put("role", worker.role);
                workerInfo.put("mergedShifts", worker.mergedShifts);
        	}
            Map<String, Object> workerInfo = new HashMap<>();
            workerInfo.put("date",date );
            workerInfo.put("name", worker.name);
            workerInfo.put("role", worker.role);
            workerInfo.put("mergedShifts", worker.mergedShifts);
            result.add(workerInfo);
        }

        return result;
    }


    public static Map<String, List<Map<String, String>>> createShiftSchedule(
    		ShiftRequirement requirement,List<WorkerShift> workers,List<Worker> worker_list,
    		PriorityQueue<Map.Entry<String, Integer>> priorityQueue,Store shift_manager)
    {

    	// 優先度でソート（降順: 優先度が高い順）
        workers.sort((w1, w2) -> Integer.compare(w1.priority,w2.priority));
        Map<String, List<Map<String, String>>> schedule = new TreeMap<>();
        //開店時間を設定
        int startMinutes = timeToMinutes(requirement.startTime);
        //閉店時間を設定
        int endMinutes = timeToMinutes(requirement.endTime);

        //開店から閉店まで１時間ごとにシフトを作成する
        for (int current = startMinutes; current < endMinutes; current += 60) {
	        //今設定する時間が入っている例：１２：００－１３：００
	        String timeSlot = minutesToTime(current) + "-" + minutesToTime(current + 60);
	        List<Map<String, String>> assignedWorkers = new ArrayList<>();
	        //キッチンかホールの必要数を取得
	        int kitchenNeeded = requirement.requiredKitchen;

	        int hallNeeded = requirement.requiredHall;
			//従業員の数だけ回る
			for (WorkerShift worker : workers) {
				//従業員の勤務可能開始時間と終了時間を取得
				int workerStart = timeToMinutes(worker.availableFrom);
				int workerEnd = timeToMinutes(worker.availableTo);

				//従業員の最大勤務可脳時間が０でないかつ勤務可能開始、終了時間内の場合通過
				if (worker.maxHours > 0 &&
					current >= workerStart &&
					current + 60 <= workerEnd) {
					//従業員の役割がキッチンの場合
					if (worker.role.equals("kitchen") && kitchenNeeded > 0) {
						Map<String, String> assignment = new HashMap<>();
						assignment.put("name", worker.name);
						assignment.put("role", worker.role);
						assignedWorkers.add(assignment);
						worker.assignedShifts.add(timeSlot);
						//最大勤務可能時間からー１
						worker.maxHours--;
						kitchenNeeded = kitchenNeeded-worker.power;
						//従業員の役割がホールの場合
					} else if (worker.role.equals("hall") && hallNeeded > 0) {
						Map<String, String> assignment = new HashMap<>();
						assignment.put("name", worker.name);
						assignment.put("role", worker.role);
						assignedWorkers.add(assignment);
						worker.assignedShifts.add(timeSlot);
						worker.maxHours--;
						hallNeeded = hallNeeded-worker.power;
					}
				}else if(worker.judge){
					Map<String, String> assignment = new HashMap<>();
					assignment.put("name", worker.name);
					assignment.put("role", worker.role);
					assignedWorkers.add(assignment);
					worker.assignedShifts.add(timeSlot);
				}



				if (kitchenNeeded <= 0 && hallNeeded <= 0) break;
			}
			schedule.put(timeSlot, assignedWorkers);
		}

        for (WorkerShift worker : workers) {
            worker.mergedShifts = mergeShifts(worker.assignedShifts,shift_manager,worker);
        }

        return schedule;
    }


    public List<Map<String,Object>> Shiftmain(
    		String work_time_start, String work_time_end,Store shift_manager, List<Shift> shift_list,
    		String date,PriorityQueue<Map.Entry<String, Integer>> priorityQueue,List<Worker> worker_list,int shift_date)
    {
    	int shift_score_set = shift_date/2;
        // 必要人数の設定（キッチンとホールで区別）
        ShiftRequirement requirement = new ShiftRequirement(work_time_start, work_time_end, shift_score_set, shift_score_set);
        List<WorkerShift> workers = new ArrayList<>();
        StoreDao stDao = new StoreDao();//StoreDao初期化


        try{
        	// バイト情報（役割も含む）
        	for(Shift shift_lists: shift_list){
        		System.out.println(shift_lists.getShiftHopeTimeId());
        		System.out.println(shift_lists.getWorker().getWorkerId());
        		String availableFrom = null;
    			String availableTo	= null;
    			int maxHours = 0;
    			Store work_time = new Store();

        		if(shift_lists.getWorker().isWorkerJudge()){
        			//社員の場合
        			if(shift_lists.getShiftHopeTimeId() != null){
        				maxHours = minutesToHour(timeToMinutes(work_time_start)-timeToMinutes(work_time_end));
        				workers.add(new WorkerShift(shift_lists.getWorker().getWorkerName(), "employee", work_time_start, work_time_end, maxHours,true,0));
        			}
        		}else{
        			//シフト希望時間ID（A,B,C,D）がある場合
        			if(shift_lists.getShiftHopeTimeId() !=null) {
        				//A,B,C,DのいずれかのIDを取得している

        				work_time = stDao.Time_get(shift_manager.getStoreId(), shift_lists.getShiftHopeTimeId());
        				//A,B,C,Dの開始時間と終了時間を取得
        				availableFrom = work_time.getWorkTimeStart().toString();
        				availableTo   = work_time.getWorkTimeEnd().toString();
        				//勤務可能最大時間を計算
        				maxHours =minutesToHour(timeToMinutes(availableTo)-timeToMinutes(availableFrom));
        				int power = Integer.parseInt(shift_lists.getWorker().getWorkerScore());
        				workers.add(new WorkerShift(
        						shift_lists.getWorker().getWorkerName(),shift_lists.getWorker().getWorkerPosition(), availableFrom, availableTo, maxHours,false,power));
        			}else{
        				if(shift_lists.getShiftHopeTimeStart() == null){
        					continue;
        				}
        				int power = Integer.parseInt(shift_lists.getWorker().getWorkerScore());
        				LocalTime timeFrom =shift_lists.getShiftHopeTimeStart().toLocalDateTime().toLocalTime();
        				LocalTime timeTo =shift_lists.getShiftHopeTimeEnd().toLocalDateTime().toLocalTime();
        				availableFrom = timeFrom.toString();
        				availableTo   = timeTo.toString();
        				maxHours =minutesToHour(timeToMinutes(availableTo)-timeToMinutes(availableFrom));
        				workers.add(new WorkerShift(shift_lists.getWorker().getWorkerName(),shift_lists.getWorker().getWorkerPosition(), availableFrom, availableTo, maxHours,false,power));
        			}
        		}
        	}

        }catch (Exception e) {
        	System.out.println(e);
		}
        //従業員の優先度登録
        for(int j =0; j< worker_list.size(); j++) {
        	Map.Entry<String, Integer> entry = priorityQueue.poll();
        		for(WorkerShift worker: workers){
        		System.out.println("workerName"+worker.name+"queueName"+entry.getKey()+"priority"+entry.getValue());
        			if(worker.name.equals(entry.getKey())){
        			   worker.setPriority(entry.getValue());
        			   break;
        			}
        		}
        }
        for(WorkerShift worker: workers){
        	System.out.println("Name:"+worker.name+"priority"+worker.priority);
        }
        createShiftSchedule(requirement, workers,worker_list,priorityQueue,shift_manager);

        // 各人の統合された勤務時間を取得
        List<Map<String, Object>> workerMergedShifts = getWorkerMergedShifts(workers,date);

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
