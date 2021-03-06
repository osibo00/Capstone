package productions.darthplagueis.capstone.fragments.gamefragments;

import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import productions.darthplagueis.capstone.R;
import uk.co.chrisjenx.calligraphy.CalligraphyUtils;

import static productions.darthplagueis.capstone.util.Constants.FONT_PATH;
import static productions.darthplagueis.capstone.util.ResourceArrayGenerator.getRandomText;

/**
 *
 */
public class DialogFragment extends Fragment {

    private View rootView;

    public DialogFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_dialog, container, false);
        setViews();
        return rootView;
    }

    private void setViews() {
        AnimationDrawable animationDrawable = (AnimationDrawable)
                rootView.findViewById(R.id.dialogue_layout).getBackground();
        animationDrawable.setEnterFadeDuration(1500);
        animationDrawable.setExitFadeDuration(2500);
        animationDrawable.start();

        TextView appName = rootView.findViewById(R.id.top_textview);
        CalligraphyUtils.applyFontToTextView(rootView.getContext(), appName, FONT_PATH);
        TextView greetingsText = rootView.findViewById(R.id.second_textview_dial);
        CalligraphyUtils.applyFontToTextView(rootView.getContext(), greetingsText, FONT_PATH);
        greetingsText.setText(getRandomText(rootView.getContext(), "greetings"));
        TextView triviaText = rootView.findViewById(R.id.third_textview_dial);
        triviaText.setText(getRandomText(rootView.getContext(), "trivia"));

        setImage();

        rootView.findViewById(R.id.ok_btn_dialogue).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getActivity() != null) {
                    getActivity().getSupportFragmentManager().beginTransaction()
                            .remove(DialogFragment.this)
                            .commit();
                }
            }
        });
    }

    private void setImage() {
        RelativeLayout layout = rootView.findViewById(R.id.card_relative_layout);
        ImageView imageView = rootView.findViewById(R.id.card_imageview);
        Glide.with(rootView.getContext())
                .load(R.drawable.starship_planets)
                .apply(new RequestOptions().override(layout.getWidth(), layout.getHeight()))
                .apply(new RequestOptions().centerCrop())
                .into(imageView);
    }
}
