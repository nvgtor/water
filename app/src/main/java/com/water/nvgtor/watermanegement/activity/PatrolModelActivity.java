package com.water.nvgtor.watermanegement.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import com.water.nvgtor.watermanegement.R;
import com.water.nvgtor.watermanegement.fragment.PatrolTasking;
import com.water.nvgtor.watermanegement.fragment.PatroledTasking;
import com.water.nvgtor.watermanegement.view.ChangeColorIconWithTextView;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by dell on 2015/8/26.
 */
public class PatrolModelActivity extends FragmentActivity implements
        ViewPager.OnPageChangeListener, View.OnClickListener
{
    private ViewPager mViewPager;
    private List<Fragment> mTabs = new ArrayList<Fragment>();
    private FragmentPagerAdapter mAdapter;
    private List<ChangeColorIconWithTextView> mTabIndicator = new ArrayList<ChangeColorIconWithTextView>();

    private ImageView img_back;
    private TextView tv_title;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.patrol_model);

        mViewPager = (ViewPager) findViewById(R.id.id_tasking_viewpager);
        img_back = (ImageView) findViewById(R.id.id_detail_back_img);
        tv_title = (TextView) findViewById(R.id.id_detail_back_title);

        tv_title.setText("巡检任务列表");

        initDatas();

        mViewPager.setAdapter(mAdapter);
        mViewPager.setOnPageChangeListener(this);
        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void initDatas()
    {
        PatrolTasking patrolTasking = new PatrolTasking();
        mTabs.add(patrolTasking);
        PatroledTasking taskDo = new PatroledTasking();
        mTabs.add(taskDo);

        mAdapter = new FragmentPagerAdapter(getSupportFragmentManager())
        {

            @Override
            public int getCount()
            {
                return mTabs.size();
            }

            @Override
            public Fragment getItem(int arg0)
            {
                return mTabs.get(arg0);
            }
        };

        initTabIndicator();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    private void initTabIndicator()
    {
        ChangeColorIconWithTextView one = (ChangeColorIconWithTextView) findViewById(R.id.id_indicator_tasking_one);
        ChangeColorIconWithTextView two = (ChangeColorIconWithTextView) findViewById(R.id.id_indicator_tasking_two);

        mTabIndicator.add(one);
        mTabIndicator.add(two);

        one.setOnClickListener(this);
        two.setOnClickListener(this);

        one.setIconAlpha(1.0f);
    }

    @Override
    public void onPageSelected(int arg0)
    {
    }

    @Override
    public void onPageScrolled(int position, float positionOffset,
                               int positionOffsetPixels)
    {
        // Log.e("TAG", "position = " + position + " , positionOffset = "
        // + positionOffset);

        if (positionOffset > 0)
        {
            ChangeColorIconWithTextView left = mTabIndicator.get(position);
            ChangeColorIconWithTextView right = mTabIndicator.get(position + 1);

            left.setIconAlpha(1 - positionOffset);
            right.setIconAlpha(positionOffset);
        }

    }

    @Override
    public void onPageScrollStateChanged(int state)
    {

    }

    @Override
    public void onClick(View v)
    {

        resetOtherTabs();

        switch (v.getId())
        {
            case R.id.id_indicator_tasking_one:
                mTabIndicator.get(0).setIconAlpha(1.0f);
                mViewPager.setCurrentItem(0, false);
                break;
            case R.id.id_indicator_tasking_two:
                mTabIndicator.get(1).setIconAlpha(1.0f);
                mViewPager.setCurrentItem(1, false);
                break;

        }

    }

    /**
     * 重置其他的Tab
     */
    private void resetOtherTabs()
    {
        for (int i = 0; i < mTabIndicator.size(); i++)
        {
            mTabIndicator.get(i).setIconAlpha(0);
        }
    }

    @Override
    public boolean onMenuOpened(int featureId, Menu menu)
    {
        if (featureId == Window.FEATURE_ACTION_BAR && menu != null)
        {
            if (menu.getClass().getSimpleName().equals("MenuBuilder"))
            {
                try
                {
                    Method m = menu.getClass().getDeclaredMethod(
                            "setOptionalIconsVisible", Boolean.TYPE);
                    m.setAccessible(true);
                    m.invoke(menu, true);
                } catch (Exception e)
                {
                }
            }
        }
        return super.onMenuOpened(featureId, menu);
    }

}
