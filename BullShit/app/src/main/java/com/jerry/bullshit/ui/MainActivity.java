package com.jerry.bullshit.ui;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.jerry.bullshit.R;
import com.jerry.bullshit.ui.fragment.AccountFragment;
import com.jerry.bullshit.ui.fragment.HomeFragment;
import com.jerry.bullshit.ui.fragment.MessageFragment;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class MainActivity extends AppCompatActivity {

    @InjectView(R.id.tab_bar)
    RadioGroup mTabBar;
    @InjectView(R.id.tab_home)
    RadioButton mHomeTab;
    @InjectView(R.id.fragment_container)
    FrameLayout mContentContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);
        initView();
        initEvent();
    }

    private void initView() {
        mHomeTab.setChecked(true);
        selectChanged(R.id.tab_home);
    }

    private void initEvent() {

        mTabBar.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                selectChanged(checkedId);
            }
        });
    }

    private void selectChanged(int checkedId){
        FragmentManager fManger = getSupportFragmentManager();
        HomeFragment homeFragment = (HomeFragment) fManger.findFragmentByTag(HomeFragment.class.getSimpleName());
        MessageFragment msgFragment = (MessageFragment) fManger.findFragmentByTag(MessageFragment.class.getSimpleName());
        AccountFragment accountFragment = (AccountFragment) fManger.findFragmentByTag(AccountFragment.class.getSimpleName());
        FragmentTransaction ft = fManger.beginTransaction();

        // 隐藏对应的fragment界面
        if (checkedId == R.id.tab_home || checkedId == R.id.tab_message || checkedId == R.id.tab_account){
            if (null != homeFragment && !homeFragment.isHidden()) {
                ft.hide(homeFragment);
            }
            if (null != msgFragment && !msgFragment.isHidden()) {
                ft.hide(msgFragment);
            }
            if (null != accountFragment && !accountFragment.isHidden()) {
                ft.hide(accountFragment);
            }
        }

        // 选中的fragment，加入事务或者直接显示
        switch (checkedId){

            case R.id.tab_home:
                if (null == homeFragment){
//                    fragment = (Fragment) Class.forName(fragmentName).newInstance();
                    ft.add(R.id.fragment_container, new HomeFragment(), HomeFragment.class.getSimpleName());
                }else {
                    ft.show(homeFragment);
                }
//                checkFragment(R.id.fragment_container, ft, homeFragment, HomeFragment.class.getSimpleName());
                break;
            case R.id.tab_discover:
                Toast.makeText(MainActivity.this, "发现", Toast.LENGTH_SHORT).show();
                break;
            case R.id.tab_add:
                Toast.makeText(MainActivity.this, "添加", Toast.LENGTH_SHORT).show();
                break;
            case R.id.tab_message:
                if (null == msgFragment){
//                    fragment = (Fragment) Class.forName(fragmentName).newInstance();
                    ft.add(R.id.fragment_container, new MessageFragment(), MessageFragment.class.getSimpleName());
                }else {
                    ft.show(msgFragment);
                }
//                checkFragment(R.id.fragment_container, ft, msgFragment, MessageFragment.class.getSimpleName());
                break;
            case R.id.tab_account:
                if (null == accountFragment){
//                    fragment = (Fragment) Class.forName(fragmentName).newInstance();
                    ft.add(R.id.fragment_container, new AccountFragment(), AccountFragment.class.getSimpleName());
                }else {
                    ft.show(accountFragment);
                }
//                checkFragment(R.id.fragment_container, ft, accountFragment, AccountFragment.class.getSimpleName());
                break;
        }

        ft.commitAllowingStateLoss();
    }

    private void checkFragment(@IdRes int containerViewId, FragmentTransaction ft, Fragment fragment, String fragmentName){

        try {
            if (null == fragment){
                fragment = (Fragment) Class.forName(fragmentName).newInstance();
                ft.add(containerViewId, fragment, fragment.getClass().getSimpleName());
            }else {
                ft.show(fragment);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void addFragment(@IdRes int containerViewId, Fragment fragment){
        FragmentManager fmanger = getSupportFragmentManager();
        FragmentTransaction transaction = fmanger.beginTransaction();
        transaction.add(containerViewId, fragment, fragment.getClass().getSimpleName());
        transaction.commit();
    }

}
