package com.example.administrator.calculatorproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView tv_main_result;
    private ArrayList<Item> items;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /**
         * 隐藏标题栏
         */
        //getSupportActionBar().hide();

        items = new ArrayList<>();

        tv_main_result = (TextView)findViewById(R.id.tv_main_result);

        findViewById(R.id.bt0).setOnClickListener(this);
        findViewById(R.id.bt1).setOnClickListener(this);
        findViewById(R.id.bt2).setOnClickListener(this);
        findViewById(R.id.bt3).setOnClickListener(this);
        findViewById(R.id.bt4).setOnClickListener(this);
        findViewById(R.id.bt5).setOnClickListener(this);
        findViewById(R.id.bt6).setOnClickListener(this);
        findViewById(R.id.bt7).setOnClickListener(this);
        findViewById(R.id.bt8).setOnClickListener(this);
        findViewById(R.id.bt9).setOnClickListener(this);
        findViewById(R.id.bt_add).setOnClickListener(this);
        findViewById(R.id.bt_subtract).setOnClickListener(this);
        findViewById(R.id.bt_multiply).setOnClickListener(this);
        findViewById(R.id.bt_divide).setOnClickListener(this);
        findViewById(R.id.bt_point).setOnClickListener(this);
        findViewById(R.id.bt_result).setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {

        switch (view.getId()){
            case R.id.bt0:
                tv_main_result.append("0");
                break;

            case R.id.bt1:
                tv_main_result.append("1");
                break;

            case R.id.bt2:
                tv_main_result.append("2");
                break;

            case R.id.bt3:
                tv_main_result.append("3");
                break;

            case R.id.bt4:
                tv_main_result.append("4");
                break;

            case R.id.bt5:
                tv_main_result.append("5");
                break;

            case R.id.bt6:
                tv_main_result.append("6");
                break;

            case R.id.bt7:
                tv_main_result.append("7");
                break;

            case R.id.bt8:
                tv_main_result.append("8");
                break;

            case R.id.bt9:
                tv_main_result.append("9");
                break;

            case R.id.bt_point:

                /**
                 * 遍历TextView，看字符串中是否包含小数点，如果包含，就不能再次输入
                 */
                String tv = tv_main_result.getText().toString();
                if(tv.contains(".")){
                    break;
                }

                tv_main_result.append(".");
                break;

            case R.id.bt_add:

                /**
                 * TextView无显示数据时，点击+，-，*，/,=不进行操作
                 */
                if(tv_main_result.getText().toString().equals("")){
                    break;
                }

                items.add(new Item(Double.parseDouble(tv_main_result.getText().toString()),Types.VALUE));
                //检查并计算
                checkAddCompute();
                items.add(new Item(0,Types.ADD));
                tv_main_result.setText("");

                break;

            case R.id.bt_subtract:

                /**
                 * TextView无显示数据时，点击+，-，*，/,=不进行操作
                 */
                if(tv_main_result.getText().toString().equals("")){
                    break;
                }

                items.add(new Item(Double.parseDouble(tv_main_result.getText().toString()),Types.VALUE));
                checkAddCompute();
                items.add(new Item(0,Types.SUBTRACT));
                tv_main_result.setText("");
                break;

            case R.id.bt_multiply:

                /**
                 * TextView无显示数据时，点击+，-，*，/,=不进行操作
                 */
                if(tv_main_result.getText().toString().equals("")){
                    break;
                }

                items.add(new Item(Double.parseDouble(tv_main_result.getText().toString()),Types.VALUE));
                checkAddCompute();
                items.add(new Item(0,Types.MULTIPLY));
                tv_main_result.setText("");
                break;

            case R.id.bt_divide:

                /**
                 * TextView无显示数据时，点击+，-，*，/,=不进行操作
                 */
                if(tv_main_result.getText().toString().equals("")){
                    break;
                }

                items.add(new Item(Double.parseDouble(tv_main_result.getText().toString()),Types.VALUE));
                checkAddCompute();
                items.add(new Item(0,Types.DIVIDE));
                tv_main_result.setText("");
                break;

            case R.id.bt_result:

                /**
                 * TextView无显示数据时，点击+，-，*，/,=不进行操作
                 */
                if(tv_main_result.getText().toString().equals("")){
                    break;
                }

                items.add(new Item(Double.parseDouble(tv_main_result.getText().toString()),Types.VALUE));
                checkAddCompute();
                tv_main_result.setText(items.get(0).number+"");
                items.clear();
                break;
        }
    }
    
    public void checkAddCompute(){
        if(items.size()>=3){

            double a = items.get(0).number;
            double b = items.get(2).number;
            int opt = items.get(1).type;

            Log.i("checkAddCompute",a+"");
            Log.i("checkAddCompute",b+"");
            Log.i("checkAddCompute",opt+"");

            //清空
            items.clear();

            switch (opt){
                case Types.ADD:
                    items.add(new Item(a+b,Types.VALUE));
                    break;

                case Types.SUBTRACT:
                    items.add(new Item(a-b,Types.VALUE));
                    break;

                case Types.MULTIPLY:
                    items.add(new Item(a*b,Types.VALUE));
                    break;

                case Types.DIVIDE:
                    items.add(new Item(a/b,Types.VALUE));
                    break;
            }
        }
    }

    public void delete(View v){
        tv_main_result.setText("");
        items.clear();
    }
}
