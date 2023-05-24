package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Stack;

public class MainActivity extends AppCompatActivity {
    FloatingActionButton one,two,three,four,five,six,seven,eight,nine,zero,equal_to,add,minus,multiply,devide,delete,delete_all,modulo;
    TextView input,result;
    String data="";
    boolean state=false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        one=findViewById(R.id.one);
        two=findViewById(R.id.two);
        three=findViewById(R.id.three);
        four=findViewById(R.id.four);
        five=findViewById(R.id.five);
        six=findViewById(R.id.six);
        seven=findViewById(R.id.seven);
        eight=findViewById(R.id.eight);
        nine=findViewById(R.id.nine);
        zero=findViewById(R.id.zero);
        add=findViewById(R.id.add);
        minus=findViewById(R.id.minus);
        devide=findViewById(R.id.devide);
        multiply=findViewById(R.id.multiply);
        modulo=findViewById(R.id.modulo);
        equal_to=findViewById(R.id.Equal_to);
        delete=findViewById(R.id.remove);
        delete_all=findViewById(R.id.delete_all);
        input=findViewById(R.id.input);
        result=findViewById(R.id.result);

        one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                data=data+"1";
                input.setText(data);
            }
        });
        two.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                data=data+"2";
                input.setText(data);
            }
        });
        three.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                data=data+"3";
                input.setText(data);
            }
        });
        four.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                data=data+"4";
                input.setText(data);
            }
        });
        five.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                data=data+"5";
                input.setText(data);
            }
        });
        six.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                data=data+"6";
                input.setText(data);
            }
        });
        seven.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                data=data+"7";
                input.setText(data);
            }
        });
        eight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                data=data+"8";
                input.setText(data);
            }
        });
        nine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                data=data+"9";
                input.setText(data);
            }
        });
        zero.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                data=data+"0";
                input.setText(data);
            }
        });
        minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                data=data+"-";
                input.setText(data);
            }
        });
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                data=data+"+";
                input.setText(data);
            }
        });
        devide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                data=data+"/";
                input.setText(data);
            }
        });
        multiply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                data=data+"*";
                input.setText(data);
            }
        });
        modulo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                data=data+"%";
                input.setText(data);
            }
        });
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(data.length()!=0){
                    data=data.substring(0,data.length()-1);
                    input.setText(data);

                    if(data.length()>1 && state){
                        int len = data.length();
                        if((data.charAt(len-1)=='+' || data.charAt(len-1)=='-' || data.charAt(len-1)=='*' || data.charAt(len-1)=='/' || data.charAt(len-1)=='%') && input.length()!=0){
                            data=data.substring(0,data.length());

                        }else{
                            int value = calculate(data);
                            result.setText(String.valueOf(value));
//                            Toast.makeText(MainActivity.this, "Please Enter expressions", Toast.LENGTH_SHORT).show();show
                        }

                    }


                }else{
                    result.setText("");
                    Toast.makeText(MainActivity.this, "All data cleared..!", Toast.LENGTH_SHORT).show();
                }

            }
        });
        delete_all.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                state=false;
                if(data.length()!=0){
                    data="";
                    input.setText(data);
                    result.setText("");
                }else{
                    Toast.makeText(MainActivity.this, "All data cleared..!", Toast.LENGTH_SHORT).show();
                }

            }
        });
        equal_to.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                state=true;
                if(input.length() != 0){
                    int value = calculate(data);
                    result.setText(String.valueOf(value));
                }else{
                    Toast.makeText(MainActivity.this, "Please Enter expressions", Toast.LENGTH_SHORT).show();
                }

            }
        });



    }

//
//
int calculate(String data) {
    Stack<Integer> integers = new Stack<>();
    Stack<Character> operators = new Stack<>();

    for (int i = 0; i < data.length(); i++) {
        char ch = data.charAt(i);

        if (ch >= '0' && ch <= '9') {
            StringBuilder operandBuilder = new StringBuilder();
            while (i < data.length() && data.charAt(i) >= '0' && data.charAt(i) <= '9') {
                operandBuilder.append(data.charAt(i));
                i++;
            }
            i--;

            int operand = Integer.parseInt(operandBuilder.toString());
            integers.push(operand);
        } else if (ch == '+' || ch == '-' || ch == '*' || ch == '/' || ch == '%') {
            while (!operators.empty() && hasPrecedence(ch, operators.peek())) {
                processOperation(integers, operators);
            }
            operators.push(ch);
        }
    }

    while (!operators.empty()) {
        processOperation(integers, operators);
    }

    if (integers.size() != 1 || !operators.empty()) {
        throw new IllegalArgumentException("Invalid expression");
    }


    try {
        while (!operators.empty()) {
            processOperation(integers, operators);
        }

        if (integers.size() != 1 || !operators.empty()) {
            throw new IllegalArgumentException("Invalid expression");
        }

        return integers.pop();
    } catch (ArithmeticException e) {

        Toast.makeText(this, "Arithmatic exceptin ocured..!", Toast.LENGTH_SHORT).show();
    } catch (IllegalArgumentException e) {

        Toast.makeText(this, "Arithmatic exceptin ocured..!", Toast.LENGTH_SHORT).show();
    } catch (Exception e) {

        Toast.makeText(this, "Arithmatic exceptin ocured..!", Toast.LENGTH_SHORT).show();
    }

    return 0;
}


    private boolean hasPrecedence(char ch1, char ch2) {
        if ((ch1 == '*' || ch1 == '/' || ch1 == '%') && (ch2 == '+' || ch2 == '-')) {
            return false;
        }
        return true;
    }

    private void processOperation(Stack<Integer> operands, Stack<Character> operators) {
        char operator = operators.pop();
        int operand2 = operands.pop();
        int operand1 = operands.pop();
        int result = 0;

        switch (operator) {
            case '+':
                result = operand1 + operand2;
                break;
            case '-':
                result = operand1 - operand2;
                break;
            case '*':
                result = operand1 * operand2;
                break;
            case '/':
                if (operand2 == 0) {
                    break;
                }
                result = operand1 / operand2;
                break;
            case '%':
                if (operand2 == 0) {
                    break;
                }
                result = operand1 % operand2;
                break;
            default:
                throw new IllegalArgumentException("Invalid operator: " + operator);
        }

       operands.push(result);

    }

}