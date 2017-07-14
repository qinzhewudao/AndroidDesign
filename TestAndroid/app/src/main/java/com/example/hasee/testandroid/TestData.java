package com.example.hasee.testandroid;

/**
 * Created by hasee on 2017/7/13.
 */

import com.example.hasee.testandroid.model.ChatModel;
import com.example.hasee.testandroid.model.ItemModel;

import java.util.ArrayList;
public class TestData {
    public static ArrayList<ItemModel> getTestAdData() {
        ArrayList<ItemModel> models = new ArrayList<>();
        ChatModel model = new ChatModel();
        model.setContent("你好？我们交个朋友吧！");
        model.setIcon("http://pic.qqtn.com/up/2017-6/2017062711010343002.jpg");
        models.add(new ItemModel(ItemModel.CHAT_A, model));
        ChatModel model2 = new ChatModel();
        model2.setContent("我是隔壁小王，你是谁？");
        model2.setIcon("http://pic.qqtn.com/up/2017-6/2017062711010489403.jpg");
        models.add(new ItemModel(ItemModel.CHAT_B, model2));
        ChatModel model3 = new ChatModel();
        model3.setContent("what？你真不知道我是谁吗？哭~");
        model3.setIcon("http://pic.qqtn.com/up/2017-6/2017062711010343002.jpg");
        models.add(new ItemModel(ItemModel.CHAT_A, model3));
        ChatModel model4 = new ChatModel();
        model4.setContent("大姐，别哭，我真不知道");
        model4.setIcon("http://pic.qqtn.com/up/2017-6/2017062711010489403.jpg");
        models.add(new ItemModel(ItemModel.CHAT_B, model4));
        ChatModel model5 = new ChatModel();
        model5.setContent("卧槽，你不知道你来撩妹？");
        model5.setIcon("http://pic.qqtn.com/up/2017-6/2017062711010343002.jpg");
        models.add(new ItemModel(ItemModel.CHAT_A, model5));
        ChatModel model6 = new ChatModel();
        model6.setContent("你是妹子，卧槽，我怎么没看出来？");
        model6.setIcon("http://pic.qqtn.com/up/2017-6/2017062711010489403.jpg");
        models.add(new ItemModel(ItemModel.CHAT_B, model6));
        return models;
    }

}
