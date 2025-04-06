package Backjoon.골드3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 웨이팅 {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        List<Customer> customers = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            String[] parts = br.readLine().split(" ");
            int reservation = Integer.parseInt(parts[0]);
            int arrival = Integer.parseInt(parts[1]);
            customers.add(new Customer(i, reservation, arrival));
        }

        // 도착 시간이 빠른 순, 도착 시간이 같으면 id 순으로 정렬
        customers.sort(Comparator.comparingInt((Customer c) -> c.arrival)
                .thenComparingInt(c -> c.id));

        // waiting: 도착 순서대로 보관 (FIFO 역할) - TreeSet을 사용해 O(log n) 삭제 지원
        TreeSet<Customer> waiting = new TreeSet<>(new Comparator<Customer>() {
            @Override
            public int compare(Customer a, Customer b) {
                if(a.arrival != b.arrival) return Integer.compare(a.arrival, b.arrival);
                return Integer.compare(a.id, b.id);
            }
        });
        // 예약한 손님들 중, 제시간 도착한 손님들을 빠르게 확인하기 위한 맵 (키: 예약 시간)
        HashMap<Integer, Customer> reservedMap = new HashMap<>();

        int index = 0;
        int currentTime = 0;
        int maxWait = 0;

        // 모든 손님이 처리될 때까지 시뮬레이션
        while(index < n || !waiting.isEmpty()){
            // 비어있으면 다음 도착 손님 시간 체크
            if(waiting.isEmpty()){
                currentTime = Math.max(currentTime, customers.get(index).arrival);
            }
            // currentTime에 도착한 모든 손님들을 waiting 큐에 추가
            while(index < n && customers.get(index).arrival <= currentTime){
                Customer c = customers.get(index);
                waiting.add(c);
                if(c.onTime) {
                    reservedMap.put(c.reservation, c);
                }
                index++;
            }

            Customer admitted;
            // 예약 시간과 일치하고 제시간 도착한 손님이 있다면 우선 입장
            if(reservedMap.containsKey(currentTime)){
                admitted = reservedMap.get(currentTime);
                waiting.remove(admitted);
                reservedMap.remove(currentTime);
            } else {
                // 예약자가 없거나 해당 시간이 아니라면 waiting 큐의 첫 번째 손님 입장
                admitted = waiting.first();
                waiting.remove(admitted);
                if(admitted.onTime && reservedMap.get(admitted.reservation) == admitted) {
                    reservedMap.remove(admitted.reservation);
                }
            }
            // 입장 시간과 도착 시간의 차이가 대기 시간
            int waitTime = currentTime - admitted.arrival;
            maxWait = Math.max(maxWait, waitTime);

            // 입장은 매 정각마다 진행되므로 시간을 1 증가
            currentTime++;
        }

        System.out.println(maxWait);
    }

    static class Customer {
        int id;
        int reservation;
        int arrival;
        boolean onTime;  // 도착 시간이 예약 시간보다 늦지 않은지 여부

        public Customer(int id, int reservation, int arrival) {
            this.id = id;
            this.reservation = reservation;
            this.arrival = arrival;
            this.onTime = arrival <= reservation;
        }
    }

}
