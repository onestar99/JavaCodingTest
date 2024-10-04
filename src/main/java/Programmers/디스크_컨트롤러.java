package Programmers;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class 디스크_컨트롤러 {

    public static int solution (int[][] jobs) {

        // jobs를 현재 시간 기준으로 정렬
        Arrays.sort(jobs, Comparator.comparing(arr -> arr[0]));
        // 현재 시간 기준으로 들어온 모든 input 작업을 pq에 집어넣고 빠르게 끝낼 수 있는 순으로 정렬하면서 들어감.
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparing(arr -> arr[1]));

        int completeJobs = 0;
        int currentTime = 0;
        int jobIdx = 0; // jobs 배열을 순회하는 인덱스
        int totalWaitingTime = 0;
        while (completeJobs < jobs.length) {


            while (jobIdx < jobs.length && jobs[jobIdx][0] <= currentTime) {
                pq.add(jobs[jobIdx]);
                jobIdx++;
            }

            if (!pq.isEmpty()) {
                int[] currentJob = pq.poll();
                currentTime += currentJob[1];
                totalWaitingTime += (currentTime - currentJob[0]);
                completeJobs++;
            } else {
                currentTime = jobs[jobIdx][0];
            }


        }
        return totalWaitingTime / jobs.length;
    }

    public static void main (String[] args) {

    }
}
