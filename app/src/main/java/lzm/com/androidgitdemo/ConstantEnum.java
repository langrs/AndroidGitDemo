package lzm.com.androidgitdemo;

public enum  ConstantEnum {
//    首页菜单文字和编码
    ONE("one",1),TWO("two",2);

    private String name;
    private int code;

    ConstantEnum(String name,int code) {
        this.name = name;
        this.code = code;
    }
    public static String getName(int index){
        for(ConstantEnum c:ConstantEnum.values()){
            if(c.getCode() == index){
                return c.getName();
            }
        }
        return null;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}