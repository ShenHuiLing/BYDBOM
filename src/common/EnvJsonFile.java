package common;

public enum EnvJsonFile {
	
	BASICFILE("\\src\\configuration\\basic_parameters.json"),
	TESTFILE("\\src\\configuration\\test_parameters.json"),
	TESTDATA("\\src\\configuration\\test_data.json");
	
	private String desc;//中文描述

    /**
     * 私有构造,防止被外部调用
     * @param desc
     */
    private EnvJsonFile(String desc){
        this.desc=desc;
    }

    /**
     * 定义方法,返回描述,跟常规类的定义没区别
     * @return
     */
    public String getDesc(){
        return desc;
    }

    public static void main(String[] args){
        for (EnvJsonFile day:EnvJsonFile.values()) {
            System.out.println("name:"+day.name()+
                    ",desc:"+day.getDesc());
        }
    }

}