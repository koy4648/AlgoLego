package day1;
import java.util.*;
import java.io.*;

public class PR258712 {
  class Solution {
    int flength;
    int[][]plist;
    public int solution(String[] friends, String[] gifts) {
      int answer = 0;
      //관계설정 2차원 배열로 i가 준 쪽 j가 받은 쪽. hashmap에 이름/숫자 넣기
      HashMap<String, Integer>fmap = new HashMap();
      flength = friends.length;
      int[] nextMonth = new int[flength];
      plist = new int[flength][flength];
      int idx=0;
      for(String friend:friends){
        fmap.put(friend,idx++);
      }

      for(String str:gifts){
        String from = str.split(" ")[0];
        String to = str.split(" ")[1];
        plist[fmap.get(from)][fmap.get(to)]++;
      }
      // System.out.println(Arrays.deepToString(plist));
      for(int i=0;i<flength;i++){
        for(int j=i;j<flength;j++){
          if(plist[i][j]>plist[j][i]){
            nextMonth[i]++;
          }else if(plist[i][j]<plist[j][i]){
            nextMonth[j]++;
          }else{//선물지수 계산
            int sumI=calcPresentNum(i);
            int sumJ=calcPresentNum(j);
            if(sumI>sumJ){
              nextMonth[i]++;
            }else if(sumI<sumJ){
              nextMonth[j]++;
            }
          }
        }
      }

      // System.out.println(Arrays.toString(nextMonth));
      int max=Integer.MIN_VALUE;
      for(int i:nextMonth){
        max=Math.max(i,max);
      }

      answer=max;
      return answer;
    }

    int calcPresentNum(int i){
      int fromI=0;
      int toI=0;
      for(int k=0;k<flength;k++){
        fromI+=plist[i][k];
        toI+=plist[k][i];
      }
      return fromI-toI;
    }
  }
}

