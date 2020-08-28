package etity;

public class Player {
    private int playerID;
    private String name;
    // 0:男， 1:女
    private int sex;
    private int age;

    @Override
    public String toString() {
        String sex_s = null;
        if (sex == 0){
            sex_s = "男";
        }else if(sex == 1){
            sex_s = "女";
        }
        return "运动员信息:" +
                "\n\t运动员ID: " + playerID +
                "\n\t姓名: " + name +
                "\n\t性别: " + sex_s +
                "\n\t年龄: " + age ;
    }

    public int getPlayerID() {
        return playerID;
    }

    public void setPlayerID(int playerID) {
        this.playerID = playerID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }


}
