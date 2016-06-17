package com.example.akshi.afpomotion;

import android.test.ActivityInstrumentationTestCase2;
import android.widget.TableRow;
import android.widget.TextView;

import com.robotium.solo.Solo;

/**
 * Created by akshi on 6/16/2016.
 */
public class MainActivityTest extends ActivityInstrumentationTestCase2<AFMainActivity> {

    private Solo solo;

    public MainActivityTest() {
        super(AFMainActivity.class);
    }

    @Override
    protected void setUp() throws Exception {
        solo=new Solo(getInstrumentation(),getActivity());

    }



    public void testMainActivity(){
        assertNotNull(getActivity());
    }


    public void testTitle1(){
        //Title 1 TestCase
        TextView textView1= (TextView) solo.getView(R.id.textView1);
        solo.waitForActivity(AFMainActivity.class);
        solo.waitForText(String.valueOf(textView1));
        assertEquals(String.valueOf(textView1.getText()),String.valueOf("Shorts Starting at $25"));

        //Title 2 TestCase
        TextView textView2= (TextView) solo.getView(R.id.textView2);
        solo.waitForActivity(AFMainActivity.class);
        solo.waitForText(String.valueOf(textView2));
        assertEquals(String.valueOf("Dolce Vita"),String.valueOf(textView2.getText()));
        //Test case for TableRow1
        TableRow tb1= (TableRow) solo.getView(R.id.tablerow1);
        solo.waitForActivity(AFMainActivity.class);
        solo.waitForView(tb1);
        solo.clickOnView(tb1);

        //Test case for TableRow2

        TableRow tb2= (TableRow) solo.getView(R.id.tablerow2);
        solo.waitForActivity(AFMainActivity.class);
        solo.waitForView(tb2);
        solo.clickOnView(tb2);

    }

    @Override
    protected void tearDown() throws Exception {
        try {
            solo.finalize();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        getActivity().finish();
        super.tearDown();
    }



}
