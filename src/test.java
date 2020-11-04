public class test {

    static private String sound = "woof woof";

    public static void main(String[] args) {

        int[] array1 = {1,2,3,4,5};

        System.out.println(array1[2]);

        method1(array1);

        // 1 | 2 | 3

    }

    //public static int[] method2(){ return array;}


    public static void method1(int[] param){

        for (int i = 0; i< param.length;i++){
            System.out.print(param[i] + "|");

        }



    }

    public static String getSound(){
        return sound;
    }
    public static void setSound(){
        sound = "bark bark";
    }

}
