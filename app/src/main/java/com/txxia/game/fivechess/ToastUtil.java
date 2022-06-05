package com.txxia.game.fivechess;

import android.content.Context;
import android.widget.Toast;

public class ToastUtil {
    //看这里！
    //ToastUtil.showMsg(MainActivity.this, msg);
    //引用的时候直接引用上面那一行就行了
    //MainActivity就是当前的文件名， msg就是要显示的字符

    public static Toast mToast;
    public static void showMsg (Context context, String msg){
        if (mToast == null){
            mToast = Toast.makeText(context, msg, Toast.LENGTH_LONG);
        }
        else{
            mToast.setText(msg);
        }
        mToast.show();
    }

}