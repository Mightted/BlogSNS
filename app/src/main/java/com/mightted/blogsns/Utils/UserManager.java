package com.mightted.blogsns.Utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.mightted.blogsns.LoginActivity;
import com.mightted.blogsns.MainActivity;
import com.mightted.blogsns.R;
import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.model.MiniProfileDrawerItem;
import com.mikepenz.materialdrawer.model.ProfileDrawerItem;
import com.mikepenz.materialdrawer.model.ProfileSettingDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IProfile;

import java.io.ByteArrayInputStream;

/**
 * Created by 晓深 on 2017/4/15.
 */

public class UserManager {

    static final UserManager manager = new UserManager();

    private AccountHeader defaultUser;
    IProfile userProfile;

    private String userName;
    private boolean loginStatus;

    private UserManager() {
        userName = MyApplication.getContext().getString(R.string.user_name);
        SharedPreferences user = MyApplication.getContext().getSharedPreferences("user", Context.MODE_PRIVATE);
        loginStatus = user.getBoolean("loginStatus",false);
    }

    public AccountHeader getDefaultUser(Activity activity) {

        if(defaultUser == null) {
            setDefaultUser(activity);
        }

        //存在登录记录
        if(getLoginStatus() && userProfile == null) {
            SharedPreferences user = MyApplication.getContext().getSharedPreferences("user", Context.MODE_PRIVATE);
            String name = user.getString("userName","");
            String email = user.getString("email","");
            String icon = user.getString("icon","");

//            if(icon.length() != 0) {
//                byte[] bytesArray = Base64.decode(icon,Base64.DEFAULT);
//                ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytesArray);
//                bitmap = BitmapFactory.decodeStream(byteArrayInputStream);
//            } else {
//                //获取图片失败，使用默认图片
//                bitmap = BitmapFactory.decodeResource(MyApplication.getContext().getResources(),R.drawable.ic_nav_head_icon);
//            }

            //虽然前面考虑将图片信息加载进SharedPreferences中，果然还是太麻烦了
            //新方法为在网络状态下加载头像信息，否则使用默认头像

            LogUtil.d("UserManager","name = " + name + "email = " + email);
            LogUtil.d("UserManager",icon);

            userProfile = new ProfileDrawerItem().withName(name)
                    .withEmail(email)
                    .withIdentifier(DrawerUtil.Account_Check);
            setIcon(icon);
            defaultUser.addProfile(userProfile,0);

        }

        return defaultUser;

    }


    private void setDefaultUser(final Activity activity) {
        defaultUser = new AccountHeaderBuilder()
                .withActivity(activity)
                .withHeaderBackground(R.drawable.img_nav_head_bg)
                .withTranslucentStatusBar(false)
                .addProfiles(
                        new ProfileSettingDrawerItem().withName(manager.getUserName()).withIcon(R.drawable.ic_nav_head_icon).withIdentifier(DrawerUtil.Account_Add)
                )
                .withOnAccountHeaderListener(new AccountHeader.OnAccountHeaderListener() {
                    @Override
                    public boolean onProfileChanged(View view, IProfile profile, boolean current) {
                        switch ((int)profile.getIdentifier()) {
                            case DrawerUtil.Account_Add:
                                //添加操作
                                Intent intent = new Intent(activity, LoginActivity.class);
                                activity.startActivityForResult(intent,DrawerUtil.Account_Login);
//                                Toast.makeText(activity,"登录中...",Toast.LENGTH_SHORT).show();
                                break;
                            case DrawerUtil.Account_Check:
                                //查看操作
                                break;
                            default:
                                break;
                        }
                        return true;
                    }
                })
                .build();
    }

    private void setIcon(String url) {

        LogUtil.i("UserManager","Set the Icon");
        if(NetworkUtil.isNetAvailable() && url.length() != 0) {
            userProfile.withIcon(url);
        } else {
            userProfile.withIcon(R.drawable.ic_nav_head_icon);
        }
    }



    public static UserManager getInstance() {
        return manager;
    }

    public void setLoginStatus(boolean status) {
        this.loginStatus = status;
    }

    public boolean getLoginStatus() {
        return loginStatus;
    }

    public String getUserName() {
        return userName;
    }

}
