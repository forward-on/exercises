package com.worthy.ly.second;

import org.junit.Test;

/**
 * Created by ly on 2018/1/13.
 */
public class Recursion {

    public static void main(String[] args) {
        Integer result = result(4);
        System.out.println(result);

        String[] strings = {"aaa","bbb","ccc","ddd","aaa"};
        boolean fun = fun(0, strings);
        System.out.println(fun);


    }

    /**
     * 计算 n!
     * @param num
     * @return
     */
    public static Integer result(Integer num) {
        if (num == 1) {
            return 1;
        } else {
            return num * result(num -1);
        }
    }

    /**
     * 判定一系列字符串中是否有相同的内容
     * @param n
     * @param a
     * @return
     */
    public static boolean fun(int n,String[] a){
        boolean b = false;
        if(n == a.length){
            b = true;
        }else{
            for(int i = n; i < a.length-1; i++){
                System.out.println(n+"    "+(i+1));
                if(a[n].equals(a[i+1])){
                    return false;
                }
            }
            n++;
            fun(n,a);
        }
        return b;
    }


    static int i = 1;    //记录步数
    //i表示进行到的步数,将编号为n的盘子由from柱移动到to柱(目标柱)
    public static void move(int n,char from,char to){
        String format = String.format("第%d步:将%d号盘子%c---->%c", i++, n, from, to);
        System.out.println(format);
    }

    //汉诺塔递归函数
//n表示要将多少个"圆盘"从起始柱子移动至目标柱子
//start_pos表示起始柱子,tran_pos表示过渡柱子,end_pos表示目标柱子
    public static void Hanio(int n,char start_pos,char tran_pos,char end_pos){
        if(n==1){    //很明显,当n==1的时候,我们只需要直接将圆盘从起始柱子移至目标柱子即可.
            move(n,start_pos,end_pos);
        }
        else{
            Hanio(n-1,start_pos,end_pos,tran_pos);   //递归处理,一开始的时候,先将n-1个盘子移至过渡柱上
            move(n,start_pos,end_pos);                //然后再将底下的大盘子直接移至目标柱子即可
            Hanio(n-1,tran_pos,start_pos,end_pos);    //然后重复以上步骤,递归处理放在过渡柱上的n-1个盘子
            //此时借助原来的起始柱作为过渡柱(因为起始柱已经空了)
        }
    }

    @Test
    public void test(){
        int n = 3;
        Hanio(n,'1','2','3');
        System.out.println("最后总的步数为" + (i-1));
    }

}
