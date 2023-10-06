package capers;

import java.io.File;
import java.io.Serializable;
import static capers.Utils.*;

/** Represents a dog that can be serialized.
 * @author Exuanbo
*/
public class Dog implements Serializable{//implements A 代表使用A的接口，Serializable 序列化 {

    /** Folder that dogs live in. */
    static final File DOG_FOLDER = join(".capers", "dogs");

    /** Age of dog. */
    private int age;
    /** Breed of dog. */
    private String breed;
    /** Name of dog. */
    private String name;
    //静态成员变量：
    //
    //DOG_FOLDER：这是一个静态的File对象，用于表示狗对象所在的文件夹。
    //age：私有的整数变量，表示狗的年龄。
    //breed：私有的字符串变量，表示狗的品种。
    //name：私有的字符串变量，表示狗的名字。

    /**
     * Creates a dog object with the specified parameters.
     * @param name Name of dog
     * @param breed Breed of dog
     * @param age Age of dog
     */
    public Dog(String name, String breed, int age) {
        this.age = age;
        this.breed = breed;
        this.name = name;
    }
    //构造函数：
    //
    //类有一个构造函数，用于创建Dog对象。构造函数接受三个参数：名字（name）、品种（breed）、年龄（age），并将这些参数初始化为对象的属性。

    /**
     * Reads in and deserializes a dog from a file with name NAME in DOG_FOLDER.
     *
     * @param name Name of dog to load
     * @return Dog read from file
     */
    public static Dog fromFile(String name) {
        File dogFile = join(DOG_FOLDER, name);
        return readObject(dogFile, Dog.class);
    }
    //fromFile 方法：
    //
    //这是一个公共静态方法，用于从文件中读取并反序列化一个狗对象。
    //方法接受一个参数：要加载的狗的名字（name）。
    //它使用文件名构建一个文件对象，然后使用readObject方法从文件中读取并反序列化一个Dog对象，最后返回该对象

    /**
     * Increases a dog's age and celebrates!
     */
    public void haveBirthday() {
        age += 1;
        System.out.println(toString());
        System.out.println("Happy birthday! Woof! Woof!");
    }
    //这是一个公共方法，用于增加狗的年龄并庆祝狗的生日。
    //它增加狗的年龄属性并打印狗的信息和生日祝福消息。

    /**
     * Saves a dog to a file for future use.
     */
    public void saveDog() {
        File fileToSave = join(DOG_FOLDER, name);
        writeObject(fileToSave, this);
    }
    //这是一个公共方法，用于将狗对象保存到文件中以供将来使用。
    //方法构建一个文件对象，并使用writeObject方法将当前对象序列化并保存到文件中。

    @Override
    public String toString() {
        return String.format(
            "Woof! My name is %s and I am a %s! I am %d years old! Woof!",
            name, breed, age);
    }
    //toString 方法：
    //
    //该方法重写了Object类的toString方法，以便打印狗的信息。
    //它返回一个格式化的字符串，包括狗的名字、品种和年龄。

}
