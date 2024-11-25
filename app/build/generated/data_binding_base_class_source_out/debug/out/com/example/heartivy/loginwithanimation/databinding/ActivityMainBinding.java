// Generated by view binder compiler. Do not edit!
package com.example.heartify.loginwithanimation.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.heartify.loginwithanimation.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ActivityMainBinding implements ViewBinding {
  @NonNull
  private final LinearLayout rootView;

  @NonNull
  public final LinearLayout activityMain;

  @NonNull
  public final Button btnSave;

  @NonNull
  public final TextView tvAge;

  @NonNull
  public final TextView tvCollestrol;

  @NonNull
  public final TextView tvDesk;

  @NonNull
  public final TextView tvEmail;

  @NonNull
  public final TextView tvIsLoveMu;

  @NonNull
  public final TextView tvJk;

  @NonNull
  public final TextView tvName;

  @NonNull
  public final TextView tvPhone;

  private ActivityMainBinding(@NonNull LinearLayout rootView, @NonNull LinearLayout activityMain,
      @NonNull Button btnSave, @NonNull TextView tvAge, @NonNull TextView tvCollestrol,
      @NonNull TextView tvDesk, @NonNull TextView tvEmail, @NonNull TextView tvIsLoveMu,
      @NonNull TextView tvJk, @NonNull TextView tvName, @NonNull TextView tvPhone) {
    this.rootView = rootView;
    this.activityMain = activityMain;
    this.btnSave = btnSave;
    this.tvAge = tvAge;
    this.tvCollestrol = tvCollestrol;
    this.tvDesk = tvDesk;
    this.tvEmail = tvEmail;
    this.tvIsLoveMu = tvIsLoveMu;
    this.tvJk = tvJk;
    this.tvName = tvName;
    this.tvPhone = tvPhone;
  }

  @Override
  @NonNull
  public LinearLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityMainBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityMainBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_main, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityMainBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      LinearLayout activityMain = (LinearLayout) rootView;

      id = R.id.btn_save;
      Button btnSave = ViewBindings.findChildViewById(rootView, id);
      if (btnSave == null) {
        break missingId;
      }

      id = R.id.tv_age;
      TextView tvAge = ViewBindings.findChildViewById(rootView, id);
      if (tvAge == null) {
        break missingId;
      }

      id = R.id.tv_collestrol;
      TextView tvCollestrol = ViewBindings.findChildViewById(rootView, id);
      if (tvCollestrol == null) {
        break missingId;
      }

      id = R.id.tv_desk;
      TextView tvDesk = ViewBindings.findChildViewById(rootView, id);
      if (tvDesk == null) {
        break missingId;
      }

      id = R.id.tv_email;
      TextView tvEmail = ViewBindings.findChildViewById(rootView, id);
      if (tvEmail == null) {
        break missingId;
      }

      id = R.id.tv_is_love_mu;
      TextView tvIsLoveMu = ViewBindings.findChildViewById(rootView, id);
      if (tvIsLoveMu == null) {
        break missingId;
      }

      id = R.id.tv_jk;
      TextView tvJk = ViewBindings.findChildViewById(rootView, id);
      if (tvJk == null) {
        break missingId;
      }

      id = R.id.tv_name;
      TextView tvName = ViewBindings.findChildViewById(rootView, id);
      if (tvName == null) {
        break missingId;
      }

      id = R.id.tv_phone;
      TextView tvPhone = ViewBindings.findChildViewById(rootView, id);
      if (tvPhone == null) {
        break missingId;
      }

      return new ActivityMainBinding((LinearLayout) rootView, activityMain, btnSave, tvAge,
          tvCollestrol, tvDesk, tvEmail, tvIsLoveMu, tvJk, tvName, tvPhone);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
