package capers;

import java.io.File;
import static capers.Utils.*;

/** A repository for Capers
 * @author Exuanbo
 * The structure of a Capers Repository is as follows:
 *
 * .capers/ -- top level folder for all persistent data in your lab12 folder
 *    - dogs/ -- folder containing all of the persistent data for dogs
 *    - story -- file containing the current story
 */
public class CapersRepository {
    /** Current Working Directory. */
    static final File CWD = new File(System.getProperty("user.dir"));
    //这是一个静态的File对象，用于表示当前工作目录（Current Working Directory）。

    /** Main metadata folder. */
    static final File CAPERS_FOLDER = join(CWD, ".capers");
    //这是一个静态的File对象，用于表示存储Capers数据的主要文件夹（metadata folder）。

    /**
     * Does required filesystem operations to allow for persistence.
     * (creates any necessary folders or files)
     * Remember: recommended structure (you do not have to follow):
     *
     * .capers/ -- top level folder for all persistent data in your lab12 folder
     *    - dogs/ -- folder containing all of the persistent data for dogs
     *    - story -- file containing the current story
     */
    public static void setupPersistence() {
        CAPERS_FOLDER.mkdir();
        Dog.DOG_FOLDER.mkdir();
    }
    //这是一个公共静态方法，用于进行必要的文件系统操作以实现持久性。
    //它创建了必要的文件夹，包括.capers文件夹和dogs文件夹，以准备持久化存储。

    /**
     * Appends the first non-command argument in args
     * to a file called `story` in the .capers directory.
     * @param text String of the text to be appended to the story
     */
    public static void writeStory(String text) {
        //这是一个公共静态方法，用于将文本追加到名为"story"的文件中。
        //如果"story"文件不存在，它将创建一个新的文件，并将文本写入其中。
        //如果"story"文件已存在，它将读取现有的内容，然后追加新的文本。
        File storyFile = join(CAPERS_FOLDER, "story");
        String newStoryContent;
        if (!storyFile.exists()) {
            newStoryContent = text;
        } else {
            String storyContent = readContentsAsString(storyFile);
            newStoryContent = storyContent + "\n" + text;
        }
        writeContents(storyFile, newStoryContent);
        System.out.println(newStoryContent);
    }

    /**
     * Creates and persistently saves a dog using the first
     * three non-command arguments of args (name, breed, age).
     * Also prints out the dog's information using toString().
     */
    public static void makeDog(String name, String breed, int age) {
        //这是一个公共静态方法，用于创建并持久保存一只狗的信息。
        //方法接受三个参数：名字（name）、品种（breed）、年龄（age）。
        //它创建一个新的Dog对象，并将其信息保存到磁盘上，然后打印出狗的信息。
        Dog newDog = new Dog(name, breed, age);
        newDog.saveDog();
        System.out.println(newDog);
    }

    /**
     * Advances a dog's age persistently and prints out a celebratory message.
     * Also prints out the dog's information using toString().
     * Chooses dog to advance based on the first non-command argument of args.
     * @param name String name of the Dog whose birthday we're celebrating.
     */
    public static void celebrateBirthday(String name) {
        Dog dogFromFile = Dog.fromFile(name);
        dogFromFile.haveBirthday();
        dogFromFile.saveDog();
        //这是一个公共静态方法，用于让一只狗过生日。
        //方法接受一个参数：狗的名字。
        //它根据名字加载一个已经存在的Dog对象，然后调用haveBirthday方法增加狗的年龄，最后保存更新后的狗的信息。
    }
}
//这个类的主要目的似乎是管理Capers仓库的数据，包括保存狗的信息和故事。
//它提供了方法来初始化持久化存储，追加故事文本，创建新的狗对象，以及为狗庆祝生日。这些操作都会对文件系统进行读写操作，以确保数据持久化。
