package com.github.note.activity;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.github.base.core.ImmersiveActivity;
import com.github.base.utils.Injector;
import com.github.base.utils.LogUtils;
import com.github.note.R;
import com.mobsandgeeks.saripaar.QuickRule;
import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;
import com.mobsandgeeks.saripaar.annotation.Order;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 表单验证
 */
public class FormValidateActivity extends ImmersiveActivity implements View.OnClickListener,Validator.ValidationListener{
    @Injector.BindView(R.id.button)
    Button button;

    @Injector.BindView(R.id.et1)
    @NotEmpty(messageResId = R.string.errorMessage)
    @Order(1)
    EditText et1;
    @Injector.BindView(R.id.et2)
    @NotEmpty(messageResId = R.string.errorMessage)
    @Order(2)
    EditText et2;
    @Injector.BindView(R.id.et3)
    @NotEmpty(messageResId = R.string.errorMessage)
    @Order(3)
    EditText et3;
    @Injector.BindView(R.id.et4)
    @NotEmpty(messageResId = R.string.errorMessage)
    @Order(4)
    EditText et4;
    @Injector.BindView(R.id.et5)
    @NotEmpty(messageResId = R.string.errorMessage)
    @Order(5)
    EditText et5;
    @Injector.BindView(R.id.et6)
    @NotEmpty(messageResId = R.string.errorMessage)
    @Order(6)
    EditText et6;

    @Injector.Res(R.mipmap.ic_error)
    Drawable errorIcon;

    protected Validator validator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_validate);
        validator = new Validator(this);
        validator.setValidationListener(this);

        // Add a quick rule
        validator.put(et1, new QuickRule<TextView>() {
            @Override
            public boolean isValid(TextView textView) {
                String phone = textView.getText().toString();
                return checkMobilePhoneNum(phone);
            }

            @Override
            public String getMessage(Context context) {
                return "请输入正确的手机号";
            }
        });
        button.setOnClickListener(this);
    }
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button:
                validator.validate();
                break;
            default:
                break;
        }
    }

    /**
     * 判断手机号
     * @param phoneNum
     * @return
     */
    public static boolean checkMobilePhoneNum(String phoneNum) {
        String regex = "^(1[3-9]\\d{9}$)";
        if (phoneNum.length() == 11) {
            Pattern p = Pattern.compile(regex);
            Matcher m = p.matcher(phoneNum);
            if (m.matches()) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void onValidationSucceeded() {
        //do somethings
        //sendSubmit();
    }

    @Override
    public void onValidationFailed(List<ValidationError> errors) {
        for (ValidationError error : errors) {
            View view = error.getView();
            String message = error.getCollatedErrorMessage(this);
            LogUtils.e(message);
            if (view instanceof EditText) {
                //((EditText) view).setError(message,getResources().getDrawable(R.mipmap.ic_error));
                // 获取到自定义图标
                // 设置图片大小
                errorIcon.setBounds(new Rect(0, 0, errorIcon.getIntrinsicWidth(), errorIcon.getIntrinsicHeight()));
                // 将提示文字改为红色
                ForegroundColorSpan fgcspan = new ForegroundColorSpan(Color.BLUE);
                SpannableStringBuilder ssbuilder = new SpannableStringBuilder(message);
                ssbuilder.setSpan(fgcspan, 0, message.length(), 0);
                //显示
                ((EditText) view).setError(message, errorIcon);
                ((EditText) view).requestFocus();
            }
            return;
        }
    }
}
