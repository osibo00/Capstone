package productions.darthplagueis.capstone.fragments.infofragments;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.List;

import productions.darthplagueis.capstone.R;
import productions.darthplagueis.capstone.abstractclasses.AbstractInfoFragment;
import productions.darthplagueis.capstone.controller.SpaceXLaunchesAdapter;
import productions.darthplagueis.capstone.model.spacex.LaunchesResponse;
import productions.darthplagueis.capstone.networking.SpaceXRetrofitFactory;

/**
 *
 */
public class SpaceXLaunchesFragment extends AbstractInfoFragment {

    private final String TAG = SpaceXLaunchesFragment.class.getSimpleName();

    private RecyclerView recyclerView;

    private String typeOfLaunch;

    @Override
    public void onCreateView() {
        recyclerView = parentView.findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(parentView.getContext(), LinearLayoutManager.VERTICAL, false));
    }

    @Override
    public void createApiCall() {
        getLaunches();
    }

    public void setTypeOfLaunch(String typeOfLaunch) {
        this.typeOfLaunch = typeOfLaunch;
    }

    private void getLaunches() {
        SpaceXRetrofitFactory.SpaceXLaunchListener listener = new SpaceXRetrofitFactory.SpaceXLaunchListener() {
            @Override
            public void launchListCallBack(List<LaunchesResponse> responseList) {
                recyclerView.setAdapter(new SpaceXLaunchesAdapter(responseList));
                progressBar.setVisibility(View.GONE);
                showFragmentSnackbar("Stay on top of SpaceX rocket launches.");
            }

            @Override
            public void onErrorCallBack(Throwable t) {
                progressBar.setVisibility(View.GONE);
                showFragmentSnackbar("Network error.");
            }
        };
        SpaceXRetrofitFactory.getInstance().setSpaceXLaunchListener(listener);
        SpaceXRetrofitFactory.getInstance().getLaunchesResponseList(typeOfLaunch);
    }
}
