package tomislav.piskur.com.vinkl;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by srs on 10.02.2018.
 */

public class FragmentAdapter extends FragmentPagerAdapter {

    private String[] tabTitle = {"Dnevni meni", "Tjedni meni"};


    public FragmentAdapter(FragmentManager fm, TjednaPonuda mainActivity) {
        super(fm);

    }

    @Override
    public Fragment getItem(int position) {

        Fragment fragment = null;

        switch (position) {
            case 0:
                fragment = new DnevniMenuFragment();
                break;
            case 1:
                fragment = new TjedniMenuFragment();
                break;

        }

        return fragment;

    }

    @Override
    public int getCount() {
        return tabTitle.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabTitle[position];
    }
}
