package com.mightted.blogsns.View;

import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebChromeClient;

import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;
import com.mightted.blogsns.R;
import com.mightted.blogsns.Utils.LogUtil;


public class ShowActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private WebView webView;
    private FloatingActionMenu floatMenu;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);
        toolbar = (Toolbar)findViewById(R.id.toolbar);
        webView = (WebView)findViewById(R.id.web_view);
        floatMenu = (FloatingActionMenu)findViewById(R.id.float_menu);

        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("这是标题");


        initUI();

        final NestedScrollView nestedScrollView = (NestedScrollView)findViewById(R.id.nested_view);
        nestedScrollView.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                if(scrollY < oldScrollY && oldScrollY - scrollY > 100) {
                    if(floatMenu.isMenuButtonHidden()) {
                        floatMenu.showMenuButton(true);
                        floatMenu.showMenu(true);
                    }

                } else if(oldScrollY < scrollY && scrollY - oldScrollY > 100) {
                    if(!floatMenu.isMenuHidden()) {
                        floatMenu.hideMenuButton(true);
                        floatMenu.hideMenu(true);
                    }

                }

            }

        });

        floatMenu.findViewById(R.id.menu_item_fab_back_top).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                webView.scrollTo(0,0);
            }
        });


    }



    private void initUI() {
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onReceivedTitle(WebView view, final String title) {
                LogUtil.i("ShowActivity",title);
                final String temp = title;
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        getSupportActionBar().setTitle(temp);
                    }
                });
            }
        });
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl("http://blog.csdn.net/soma5431/article/details/70157695");
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
            default:
                Toast.makeText(ShowActivity.this,"点击了别的什么东西",Toast.LENGTH_SHORT).show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }


}
