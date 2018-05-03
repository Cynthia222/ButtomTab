package com.cynthia.buttomtabdemo;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by cynthia on 18/5/3.
 */

public class MainActivity extends Activity implements RadioGroup.OnCheckedChangeListener {
    private MainFragment mMainfragment;
    private MessageFragment mMessageFragment;
    private SendFragment mSendFragment;
    private FriendsFragment mFriendFragment;
    private MineFragment mMineFragment;
    private Fragment[] mFragments;
    private RadioGroup mRadioGroup;
    private ImageView mSendImg;
    private TextView mSendTv;
    private RelativeLayout mSendrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();

        initFragment();
    }
    private void initView(){
        mRadioGroup=findViewById(R.id.rg_tab);
        mSendImg=findViewById(R.id.send_igv);
        mSendTv=findViewById(R.id.send_tv);
        mSendrl=findViewById(R.id.send_rl);
        mRadioGroup.setOnCheckedChangeListener(this);

    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
       FragmentManager manager = getFragmentManager();
       FragmentTransaction transaction = manager.beginTransaction();
        switch (checkedId) {
            case R.id.main_rb:
                transaction.replace(R.id.content_frag, mFragments[0]);

                break;
            case R.id.message_rb:
                transaction.replace(R.id.content_frag, mFragments[1]);

                break;
            case R.id.friend_rb:
                transaction.replace(R.id.content_frag, mFragments[3]);

                break;
            case R.id.info_rb:
                transaction.replace(R.id.content_frag, mFragments[4]);

                break;
        }
        mSendImg.setImageResource(R.drawable.send_tab_no);
        mSendTv.setTextColor(getResources().getColor(R.color.RGB_AAB2BD));
        transaction.commit();


    }

    private void initFragment() {
        mMainfragment = new MainFragment();
        mMessageFragment = new MessageFragment();
        mSendFragment = new SendFragment();
        mFriendFragment = new FriendsFragment();
        mMineFragment = new MineFragment();
        mFragments = new Fragment[5];
        mFragments[0] = mMainfragment;
        mFragments[1] = mMessageFragment;
        mFragments[2] = mSendFragment;
        mFragments[3] = mFriendFragment;
        mFragments[4] = mMineFragment;

        //获取Fragment管理器
        FragmentManager manager = getFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.content_frag, mFragments[0]);
        //提交事物
        transaction.commit();
        //默认点击首页
        mRadioGroup.check(R.id.main_rb);
        mSendrl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mRadioGroup.clearCheck();
                mSendImg.setImageResource(R.drawable.send_tab);
                mSendTv.setTextColor(getResources().getColor(R.color.RGB_009900));

                FragmentManager manager = getFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();
                transaction.replace(R.id.content_frag, mFragments[2]);
                transaction.commit();

            }
        });


    }

}
