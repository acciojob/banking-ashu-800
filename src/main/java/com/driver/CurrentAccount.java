package com.driver;

import java.util.PriorityQueue;

public class CurrentAccount extends BankAccount{
    String tradeLicenseId; //consists of Uppercase English characters only

    public CurrentAccount(String name, double balance, String tradeLicenseId) throws Exception {
        // minimum balance is 5000 by default. If balance is less than 5000, throw "Insufficient Balance" exception
        super(name,balance,5000);
        this.tradeLicenseId=tradeLicenseId;
        try {
            if (this.getBalance() < 5000) {
                throw new RuntimeException("Insufficient Balance");
            }
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

    class Pair{
        char ch;
        int f;
        Pair(char ch, int f){
            this.ch=ch;
            this.f=f;
        }
    }
    public void validateLicenseId() throws Exception {
        // A trade license Id is said to be valid if no two consecutive characters are same
        // If the license Id is valid, do nothing
        // If the characters of the license Id can be rearranged to create any valid license Id
        // If it is not possible, throw "Valid License can not be generated" Exception
        boolean flag = true;

        String s = this.tradeLicenseId;

        for (int i = 0; i < s.length() - 1; i++) {
            if (s.charAt(i) == s.charAt(i + 1)) {
                flag = false;
            }
        }

        if (flag == false) {

            int[] map = new int[26];
            for (int i = 0; i < s.length(); i++) {
                char c = s.charAt(i);
                map[c - 'a']++;
            }
            PriorityQueue<Pair> pq = new PriorityQueue<>((a, b) -> (b.f - a.f));
            for (int i = 0; i < 26; i++) {
                if (map[i] > 0)
                    pq.add(new Pair((char) (i + 'a'), map[i]));
            }
            StringBuilder sb = new StringBuilder();
            Pair block = pq.remove();
            block.f--;
            sb.append(block.ch);
            while (pq.size() > 0) {
                Pair temp = pq.remove();
                sb.append(temp.ch);
                temp.f--;
                if (block.f > 0) {
                    pq.add(block);
                }
                block = temp;
            }
            try {
                if (block.f > 0) {
                    throw new RuntimeException("Valid License can not be generated");
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }

        }
    }

}
