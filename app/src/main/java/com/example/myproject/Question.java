package com.example.myproject;

public class Question {
    public int qno;
    public String ques;
    public String opt1, opt2, opt3, opt4;
    public String ans, choice;

    public Question(int qno,String q,String o1,String o2,String o3,String o4,String a,String ch){
        this.qno = qno;
        this.ques = q;
        this.opt1 = o1; this.opt2 = o2;
        this.opt3 = o3; this.opt4 = o4;
        this.ans = a; this.choice = ch;
    }
}
