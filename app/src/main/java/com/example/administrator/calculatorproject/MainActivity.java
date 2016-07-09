package com.example.administrator.calculatorproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.math.BigDecimal;
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
                if(tv_main_result.getText().toString().equals("0")){
                    break;
                }
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
                 * 如果显示文本框中没有数据，小数点的输入无效
                 */
                if(tv_main_result.getText().toString().equals("")){
                    break;
                }

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
                double number = items.get(0).number;
                tv_main_result.setText(number+"");
                Log.i("number",number+"");
                items.clear();
                break;
        }
    }

    public void checkAddCompute(){
        if(items.size()>=3){

            double a = items.get(0).number;
            BigDecimal a1 = new BigDecimal(Double.toString(a));
            double b = items.get(2).number;
            BigDecimal b1 = new BigDecimal(Double.toString(b));

            int opt = items.get(1).type;

            Log.i("checkAddCompute","a"+a);
            Log.i("checkAddCompute","b"+b);
            Log.i("checkAddCompute","opt"+opt);

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
                    String s = a1.multiply(b1).toString();
                    double s1 = Double.parseDouble(s);
                    items.add(new Item(s1,Types.VALUE));
                    break;

                case Types.DIVIDE:
                    String s2 = a1.divide(b1).toString();
                    double s3 = Double.parseDouble(s2);
                    items.add(new Item(s3,Types.VALUE));
                    break;
            }
        }
    }

    public void delete(View v){
        tv_main_result.setText("");
        items.clear();
    }
}
