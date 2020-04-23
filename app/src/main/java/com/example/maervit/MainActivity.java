
package com.example.maervit;

        import android.Manifest;
        import android.content.Context;
        import android.content.Intent;
        import android.content.pm.PackageManager;
        import android.content.pm.PermissionInfo;
        import android.os.Bundle;
        import androidx.appcompat.app.AppCompatActivity;
        import androidx.core.content.ContextCompat;

        import android.view.View;
        import android.view.Menu;
        import android.view.MenuItem;
        import android.widget.Toast;
        import me.dm7.barcodescanner.zxing.ZXingScannerView;


public class MainActivity extends AppCompatActivity {

    private ZXingScannerView scannerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }

    public  void scanCode (View v) {

        if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {
            //DIALOG BOX

        }
        scannerView = new ZXingScannerView(this);
        scannerView.setResultHandler(new ZXingScannerResultHandler());

        setContentView(scannerView);
        scannerView.startCamera();



    }

    public void otvorChladnicku(View view){
        Intent intent = new Intent(this, Chladnicka.class);
        startActivity(intent);
    }

    @Override
    public void onPause(){
        super.onPause();
        this.finish();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    class ZXingScannerResultHandler implements ZXingScannerView.ResultHandler{

        @Override
        public void handleResult(com.google.zxing.Result result) {
            String resultCodeString = result.getText();
            long resultCode = Long.parseLong(resultCodeString);
            long liner = 884851041913L;
            if (resultCode == liner){
                Toast.makeText(MainActivity.this, "prave si naskenoval liner", Toast.LENGTH_LONG).show();
                openMainActivity2();
            }else{
                Toast.makeText(MainActivity.this, "neurcene", Toast.LENGTH_SHORT).show();
            }

            setContentView(R.layout.activity_main);
            scannerView.stopCamera();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        //SimplifiableIfStatement
        switch (item.getItemId()){
            case R.id.about:
                //Toast.makeText(this, "About us", Toast.LENGTH_LONG).show();
                openOnas();
                return true;
            case R.id.faq:
                //Toast.makeText(this, "Faq", Toast.LENGTH_LONG).show();
                openFAQ();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }


    public void openMainActivity2(){
        Intent intent = new Intent(this, Main2Activity.class);
        startActivity(intent);
    }

    public void openOnas(){
        Intent intent = new Intent(this, Onas.class);
        startActivity(intent);
    }
    public void openFAQ(){
        Intent intent = new Intent(this, Faq.class);
        startActivity(intent);
    }
}




