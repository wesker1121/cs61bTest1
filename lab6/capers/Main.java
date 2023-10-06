package capers;

import java.io.File;

import static capers.Utils.*;

public class Main {
    //: 这是程序的入口点。根据命令行参数，它会执行不同的操作，如添加故事、创建狗狗、或者庆祝狗狗的生日。
    public static void main(String[] args) {
        if (args.length == 0) {
            Utils.exitWithError("Must have at least one argument");
        }

        CapersRepository.setupPersistence();    //这个方法初始化了持久性数据存储，创建了一个存储 .capers 文件夹的目录结构。
        String text;
        switch (args[0]) {  //根据第一个命令行参数来判断要执行的操作
            case "story":   //如果命令是 "story"，则将给定的文本添加到当前故事中并将其打印出来。
                /* This call has been handled for you. The rest will be similar. */
                validateNumArgs("story", args, 2);
                text = args[1];
                CapersRepository.writeStory(text);
                break;
            case "dog":     //如果命令是 "dog"，则创建一个狗狗对象，并将其详细信息打印出来。
                validateNumArgs("dog", args, 4);
                CapersRepository.makeDog(args[1], args[2], Integer.parseInt(args[3]));
                break;
            case "birthday":    //如果命令是 "birthday"，则庆祝指定狗狗的生日，并打印出庆祝信息。
                validateNumArgs("birthday", args, 2);
                CapersRepository.celebrateBirthday(args[1]);
                break;
            default:            // 如果命令无法识别，则输出错误信息。
                exitWithError(String.format("Unknown command: %s", args[0]));
        }
        return;
    }

    public static void validateNumArgs(String cmd, String[] args, int n) {
        //这个方法用于检查给定命令的参数数量是否正确，如果不正确则抛出异常。
        if (args.length != n) {
            throw new RuntimeException(
                    String.format("Invalid number of arguments for: %s.", cmd));
        }
    }
}
//该程序主要用于管理一个包含狗狗信息和故事的持久性数据存储，用户可以通过命令行界面执行不同的操作，例如添加故事、创建狗狗或庆祝狗狗的生日。
//这些操作都会与 .capers 文件夹中的数据交互，以实现持久性数据的管理