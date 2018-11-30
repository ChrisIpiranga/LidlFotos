package com.example.picanova.lidlfotos;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.webkit.HttpAuthHandler;
import android.webkit.JavascriptInterface;
import android.webkit.ValueCallback;
import android.webkit.WebResourceError;
import android.webkit.WebResourceResponse;
import android.webkit.WebSettings;
import android.widget.ProgressBar;
import android.support.design.widget.FloatingActionButton;
import android.webkit.WebBackForwardList;
import android.view.View;
import android.view.MenuItem;
import android.webkit.WebView;
import android.app.AlertDialog;
import android.webkit.WebResourceRequest;
import android.widget.ScrollView;
import android.widget.TextView;
import android.webkit.WebViewClient;
import android.content.DialogInterface;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.webkit.WebChromeClient;
import android.view.Menu;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private static final String TAG = MainActivity.class.getSimpleName();
    private String mCM;
    private ValueCallback<Uri> mUM;
    private ValueCallback<Uri[]> mUMA;
    private final static int FCR=1;
    private int UPCount;
    private int DOWNCount;

    private boolean reduceAlpha;
    private boolean firstLoad;
    private boolean AuthRequest;

    private int basketTotal = 0;
    private WebView browser;

    private TextView menuLabel;
    private TextView text_cartCount;
    private Menu menu;

    private ProgressBar progrbar;
    private NestedScrollView List_Categories;
    private FloatingActionButton HomeButton;

    private String domainURL;

    public MainActivity() {
        reduceAlpha = false;
        firstLoad = false;
        AuthRequest = false;
    }

    private void LoadTitle(String urlToLoad) {

        //Log.e(TAG, "LOG-URL: " + urlToLoad);

        if (urlToLoad != null && !urlToLoad.isEmpty() && !urlToLoad.equals("null") && !firstLoad) {

            if (urlToLoad.contains(domainURL) && urlToLoad.contains(".jsf") && !urlToLoad.contains("javax.faces")) {

                if (urlToLoad.contains(getString(R.string.url_help_contact))) {
                    Objects.requireNonNull(menuLabel).setText(R.string.menu_help_contact);
                } else if (urlToLoad.contains(getString(R.string.url_order_status))) {
                    Objects.requireNonNull(menuLabel).setText(R.string.menu_order_status);
                } else if (urlToLoad.contains(getString(R.string.url_formats_prices))) {
                    Objects.requireNonNull(menuLabel).setText(R.string.menu_formats_prices);
                } else if (urlToLoad.contains(getString(R.string.url_shipping))) {
                    Objects.requireNonNull(menuLabel).setText(R.string.menu_shipping);
                } else if (urlToLoad.contains(getString(R.string.url_secure_payment))) {
                    Objects.requireNonNull(menuLabel).setText(R.string.menu_secure_payment);
                } else if (urlToLoad.contains(getString(R.string.url_logIn))) {
                    Objects.requireNonNull(menuLabel).setText(R.string.menu_logIn);
                } else if (urlToLoad.contains(getString(R.string.url_terms_conditions))) {
                    Objects.requireNonNull(menuLabel).setText(R.string.menu_terms_conditions);
                } else if (urlToLoad.contains(getString(R.string.url_data_protection))) {
                    Objects.requireNonNull(menuLabel).setText(R.string.menu_data_protection);

                } else if (urlToLoad.contains(getString(R.string.url_fotos))) {
                    Objects.requireNonNull(menuLabel).setText(R.string.cat_fotos);
                } else if (urlToLoad.contains(getString(R.string.url_fotoabzuge))) {
                    Objects.requireNonNull(menuLabel).setText(R.string.cat_fotoabzuge);
                } else if (urlToLoad.contains(getString(R.string.url_retroprints))) {
                    Objects.requireNonNull(menuLabel).setText(R.string.cat_retroprints);
                } else if (urlToLoad.contains(getString(R.string.url_poster))) {
                    Objects.requireNonNull(menuLabel).setText(R.string.cat_poster);

                } else if (urlToLoad.contains(getString(R.string.url_fotobook))) {
                    Objects.requireNonNull(menuLabel).setText(R.string.cat_fotobook);
                } else if (urlToLoad.contains(getString(R.string.url_fotobook_mini))) {
                    Objects.requireNonNull(menuLabel).setText(R.string.cat_fotobook_mini);
                } else if (urlToLoad.contains(getString(R.string.url_fotobook_small))) {
                    Objects.requireNonNull(menuLabel).setText(R.string.cat_fotobook_small);
                } else if (urlToLoad.contains(getString(R.string.url_fotobook_big))) {
                    Objects.requireNonNull(menuLabel).setText(R.string.cat_fotobook_big);
                } else if (urlToLoad.contains(getString(R.string.url_fotobook_squared_l))) {
                    Objects.requireNonNull(menuLabel).setText(R.string.cat_fotobook_squared_l);
                } else if (urlToLoad.contains(getString(R.string.url_fotobook_suqared_xl))) {
                    Objects.requireNonNull(menuLabel).setText(R.string.cat_fotobook_squared_xl);
                } else if (urlToLoad.contains(getString(R.string.url_fotobook_suqared_xxl))) {
                    Objects.requireNonNull(menuLabel).setText(R.string.cat_fotobook_squared_xxl);

                } else if (urlToLoad.contains(getString(R.string.url_fotocalendar))) {
                    Objects.requireNonNull(menuLabel).setText(R.string.cat_fotocalendar);
                } else if (urlToLoad.contains(getString(R.string.url_wallcalender))) {
                    Objects.requireNonNull(menuLabel).setText(R.string.cat_wallcalender);
                } else if (urlToLoad.contains(getString(R.string.url_wallcalender_squared))) {
                    Objects.requireNonNull(menuLabel).setText(R.string.cat_wallcalender_squared);
                } else if (urlToLoad.contains(getString(R.string.url_wallcalender_kitchen))) {
                    Objects.requireNonNull(menuLabel).setText(R.string.cat_wallcalender_kitchen);

                } else if (urlToLoad.contains(getString(R.string.url_wallpicture))) {
                    Objects.requireNonNull(menuLabel).setText(R.string.cat_wallpictures);

                } else if (urlToLoad.contains(getString(R.string.url_canvas))) {
                    Objects.requireNonNull(menuLabel).setText(R.string.cat_canvas);
                } else if (urlToLoad.contains(getString(R.string.url_alu_dibond))) {
                    Objects.requireNonNull(menuLabel).setText(R.string.cat_alu_dibond);
                } else if (urlToLoad.contains(getString(R.string.url_acrylglas))) {
                    Objects.requireNonNull(menuLabel).setText(R.string.cat_acrylglas);
                } else if (urlToLoad.contains(getString(R.string.url_forex))) {
                    Objects.requireNonNull(menuLabel).setText(R.string.cat_forex);
                } else if (urlToLoad.contains(getString(R.string.url_galleryprint))) {
                    Objects.requireNonNull(menuLabel).setText(R.string.cat_galleryprint);

                } else if (urlToLoad.contains(getString(R.string.url_fotogift))) {
                    Objects.requireNonNull(menuLabel).setText(R.string.cat_fotogift);
                } else if (urlToLoad.contains(getString(R.string.url_acrylblock))) {
                    Objects.requireNonNull(menuLabel).setText(R.string.cat_acrylblock);
                } else if (urlToLoad.contains(getString(R.string.url_mug))) {
                    Objects.requireNonNull(menuLabel).setText(R.string.cat_mug);
                } else if (urlToLoad.contains(getString(R.string.url_puzzle))) {
                    Objects.requireNonNull(menuLabel).setText(R.string.cat_puzzle);
                } else if (urlToLoad.contains(getString(R.string.url_phonecase))) {
                    Objects.requireNonNull(menuLabel).setText(R.string.cat_phonecase);
                } else if (urlToLoad.contains(getString(R.string.url_blanket))) {
                    Objects.requireNonNull(menuLabel).setText(R.string.cat_blanket);
                } else if (urlToLoad.contains(getString(R.string.url_cushion))) {
                    Objects.requireNonNull(menuLabel).setText(R.string.cat_cushion);
                } else if (urlToLoad.contains(getString(R.string.url_totbag))) {
                    Objects.requireNonNull(menuLabel).setText(R.string.cat_totbag);
                } else if (urlToLoad.contains(getString(R.string.url_towel))) {
                    Objects.requireNonNull(menuLabel).setText(R.string.cat_towel);
                } else if (urlToLoad.contains(getString(R.string.url_mousepad))) {
                    Objects.requireNonNull(menuLabel).setText(R.string.cat_mousepad);

                } else if (urlToLoad.contains(getString(R.string.url_photocards))) {
                    Objects.requireNonNull(menuLabel).setText(R.string.cat_photocard);
                } else if (urlToLoad.contains(getString(R.string.url_grusskarte))) {
                    Objects.requireNonNull(menuLabel).setText(R.string.cat_fotocard);
                } else if (urlToLoad.contains(getString(R.string.url_klappkarte))) {
                    Objects.requireNonNull(menuLabel).setText(R.string.cat_foldcard);
                } else if (urlToLoad.contains(getString(R.string.url_photocards))) {
                    Objects.requireNonNull(menuLabel).setText(R.string.url_postkarte);

                } else if (urlToLoad.contains(getString(R.string.url_basket))) {
                    Objects.requireNonNull(menuLabel).setText(R.string.cat_basket);
                } else if (urlToLoad.contains(getString(R.string.url_basket_en))) {
                    Objects.requireNonNull(menuLabel).setText(R.string.cat_basket);

                } else if (urlToLoad.contains(getString(R.string.url_address))) {
                    Objects.requireNonNull(menuLabel).setText(R.string.cat_addresses);
                } else if (urlToLoad.contains(getString(R.string.url_payment))) {
                    Objects.requireNonNull(menuLabel).setText(R.string.cat_payment);
                } else if (urlToLoad.contains(getString(R.string.url_confirmorder))) {
                    Objects.requireNonNull(menuLabel).setText(R.string.cat_confirmorder);

                } else if (urlToLoad.contains(getString(R.string.url_configurator))) {
                    Objects.requireNonNull(menuLabel).setText(R.string.cat_configurator);
                } else if (urlToLoad.contains(getString(R.string.url_info))) {
                    Objects.requireNonNull(menuLabel).setText("INFO: Debbug!");
                } else {
                    Objects.requireNonNull(menuLabel).setText(R.string.cat_undefined);
                }
            }
        }
    }

    private void ShowCatalog() {
        this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                browser.setVisibility(View.GONE);
                progrbar.setVisibility(View.GONE);
                List_Categories.setVisibility(View.VISIBLE);
                List_Categories.bringToFront();
                Objects.requireNonNull(menuLabel).setText(R.string.title_product_overview);
                Objects.requireNonNull(getSupportActionBar()).show();
                HideHomeButton();
            }
        });
    }

    private void ShowLoading() {
        ShowLoading(reduceAlpha);
    }

    private void ShowLoading(Boolean reduceAlpha) {

        final Boolean reduceAlphaFinal = reduceAlpha;

        this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (reduceAlphaFinal) {
                    browser.setVisibility(View.VISIBLE);
                    browser.bringToFront();
                    browser.setAlpha(0.5f);
                } else {
                    Toast.makeText(getApplicationContext(), R.string.label_loading, Toast.LENGTH_SHORT).show();
                    browser.setVisibility(View.GONE);
                }

                HideHomeButton();
                LoadTitle(browser.getUrl());

                progrbar.setVisibility(View.VISIBLE);
                progrbar.bringToFront();

                List_Categories.setVisibility(View.GONE);
            }
        });
    }

    private void HideLoading() {
        this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                progrbar.setVisibility(View.GONE);
                browser.setVisibility(View.VISIBLE);
                browser.bringToFront();
                browser.setAlpha(1f);
            }
        });
    }

    private void AlphaBrowser() {
        this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                browser.setAlpha(0.5f);
                progrbar.setVisibility(View.VISIBLE);
                progrbar.bringToFront();
            }
        });
    }

    private File createImageFile() throws IOException{
        // Create an image file
        @SuppressLint("SimpleDateFormat")
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "img_"+timeStamp+"_";
        File storageDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
        return File.createTempFile(imageFileName,".jpg",storageDir);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig){
        super.onConfigurationChanged(newConfig);
    }

    @SuppressLint("SetJavaScriptEnabled")
    private void LoadURL(String urlToLoad) {

        String USerAgentInfo = " Picanova-Android-Browser-App";
        String UTM_Params = "utm_source=LIDL-Fotos-App";

        ShowLoading();

        urlToLoad = domainURL.concat(urlToLoad);
        urlToLoad = urlToLoad.concat(urlToLoad.contains("?") ? "&" : "?");
        urlToLoad = urlToLoad.concat(UTM_Params);

        if (!urlToLoad.equals(browser.getUrl()) || urlToLoad.contains(getString(R.string.url_basket)) || urlToLoad.contains(getString(R.string.url_basket_en)) || true == true) {

            class MyJavaScriptInterface {

                private Context ctx;

                MyJavaScriptInterface(Context ctx) {
                    this.ctx = ctx;
                }

                @JavascriptInterface
                @SuppressWarnings("unused")
                public void showHTML(String html) {

                    try {
                        if (html.trim().isEmpty()) {
                            basketTotal = 0;
                        } else {
                            basketTotal = Integer.parseInt(html.trim());
                        }
                    } catch (NumberFormatException nfe) {
                        // Handle parse error.
                    }

                    ShowBasket(ctx);
                }
            }

            // Enable javascript
            browser.getSettings().setJavaScriptEnabled(true);
            browser.getSettings().setLoadWithOverviewMode(true);
            browser.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);
            browser.getSettings().setUseWideViewPort(true);
            browser.getSettings().setAllowFileAccess(true);
            browser.getSettings().setAllowContentAccess(true);
            browser.getSettings().supportZoom();

            if(Build.VERSION.SDK_INT >= 21){
                browser.getSettings().setMixedContentMode(0);
                browser.setLayerType(View.LAYER_TYPE_HARDWARE, null);
            }else if(Build.VERSION.SDK_INT >= 19){
                browser.setLayerType(View.LAYER_TYPE_HARDWARE, null);
            }else if(Build.VERSION.SDK_INT < 19){
                browser.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
            }

            browser.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
            browser.addJavascriptInterface(new MyJavaScriptInterface(this), "HtmlViewer");

            if (!browser.getSettings().getUserAgentString().contains(USerAgentInfo)) {
                browser.getSettings().setUserAgentString(browser.getSettings().getUserAgentString().concat(USerAgentInfo));
            }

            // Set WebView client
            browser.setWebChromeClient(new WebChromeClient() {
                //For Android 3.0+
                public void openFileChooser(ValueCallback<Uri> uploadMsg){
                    mUM = uploadMsg;
                    Intent i = new Intent(Intent.ACTION_GET_CONTENT);
                    i.addCategory(Intent.CATEGORY_OPENABLE);
                    i.setType("*/*");
                    MainActivity.this.startActivityForResult(Intent.createChooser(i,getString(R.string.image_chooser)), FCR);
                }
                // For Android 3.0+, above method not supported in some android 3+ versions, in such case we use this
                public void openFileChooser(ValueCallback uploadMsg, String acceptType){
                    mUM = uploadMsg;
                    Intent i = new Intent(Intent.ACTION_GET_CONTENT);
                    i.addCategory(Intent.CATEGORY_OPENABLE);
                    i.setType("*/*");
                    MainActivity.this.startActivityForResult(
                            Intent.createChooser(i, getString(R.string.image_chooser)),
                            FCR);
                }
                //For Android 4.1+
                public void openFileChooser(ValueCallback<Uri> uploadMsg, String acceptType, String capture){
                    mUM = uploadMsg;
                    Intent i = new Intent(Intent.ACTION_GET_CONTENT);
                    i.addCategory(Intent.CATEGORY_OPENABLE);
                    i.setType("*/*");
                    MainActivity.this.startActivityForResult(Intent.createChooser(i, getString(R.string.image_chooser)), MainActivity.FCR);
                }
                //For Android 5.0+
                public boolean onShowFileChooser(
                        WebView webView, ValueCallback<Uri[]> filePathCallback,
                        WebChromeClient.FileChooserParams fileChooserParams){
                    if(mUMA != null){
                        mUMA.onReceiveValue(null);
                    }
                    mUMA = filePathCallback;
                    Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    if(takePictureIntent.resolveActivity(MainActivity.this.getPackageManager()) != null){
                        File photoFile = null;
                        try{
                            photoFile = createImageFile();
                            takePictureIntent.putExtra("PhotoPath", mCM);
                        }catch(IOException ex){
                            //Log.e(TAG, getString(R.string.image_file_failed), ex);
                        }
                        if(photoFile != null){
                            mCM = "file:" + photoFile.getAbsolutePath();
                            takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(photoFile));
                        }else{
                            takePictureIntent = null;
                        }
                    }
                    Intent contentSelectionIntent = new Intent(Intent.ACTION_GET_CONTENT);
                    contentSelectionIntent.addCategory(Intent.CATEGORY_OPENABLE);
                    contentSelectionIntent.setType("*/*");
                    Intent[] intentArray;
                    if(takePictureIntent != null){
                        intentArray = new Intent[]{takePictureIntent};
                    }else{
                        intentArray = new Intent[0];
                    }

                    Intent chooserIntent = new Intent(Intent.ACTION_CHOOSER);
                    chooserIntent.putExtra(Intent.EXTRA_INTENT, contentSelectionIntent);
                    chooserIntent.putExtra(Intent.EXTRA_TITLE, getString(R.string.image_chooser));
                    chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS, intentArray);
                    startActivityForResult(chooserIntent, FCR);
                    return true;
                }
            });

            browser.setWebViewClient(new WebViewClient() {

                private Boolean trueError = false;

                @Override
                public WebResourceResponse shouldInterceptRequest(WebView view, WebResourceRequest request) {

                    String currentUrl = request.getUrl().toString();

                    if (request.getRequestHeaders().containsValue("partial/ajax") && (currentUrl.contains(getString(R.string.url_basket)) || currentUrl.contains(getString(R.string.url_basket_en)))) {
                        request.getRequestHeaders().put("Refresh", "10;url=" + currentUrl);
                    } else {
                        LoadTitle(request.getUrl().toString());
                    }

                    return super.shouldInterceptRequest(view, request);
                }

                @Override
                public void onPageStarted(WebView view, String url, Bitmap favicon) {
                    if (url.contains(domainURL) && !url.contains(getString(R.string.url_configurator))) {
                        AlphaBrowser();
                    }

                    AuthRequest = false;
                    super.onPageStarted(view, url, favicon);
                }

                @Override
                public void onPageFinished(WebView view, String url) {
                    super.onPageFinished(view, url);

                    if (firstLoad) {
                        ShowCatalog();
                        firstLoad = false;
                    } else {
                        HideLoading();
                        HideHomeButton();
                        ApplyScrollListener();

                        if (trueError) {
                            ShowCatalog();
                            trueError = false;
                        }
                    }

                    browser.loadUrl("javascript:window.HtmlViewer.showHTML" +
                            "($(\"#numberOfItems .cartCount\").text().trim());");
                }

                @Override
                public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                    view.loadUrl(request.getUrl().toString());
                    return true;
                }

                @Override
                public void onReceivedLoginRequest(WebView view, String realm, String account, String args) {
                    // TODO Auto-generated method stub
                    Toast.makeText(getApplicationContext(), "Logging in..", Toast.LENGTH_SHORT).show();
                    super.onReceivedLoginRequest(view, realm, account, args);
                }

                @Override
                public void onReceivedHttpAuthRequest(WebView view, final HttpAuthHandler handler, final String host, final String realm ){
                    Toast.makeText(getApplicationContext(), "Logging in (Auth required)..", Toast.LENGTH_SHORT).show();
                    handler.proceed("test", "karneval2");
                    AuthRequest = true;
                }

                @Override
                public void onReceivedHttpError (WebView view, WebResourceRequest request, WebResourceResponse errorResponse) {
                    if (request.getUrl().toString().contains(domainURL) && !AuthRequest) {
                        Toast.makeText(view.getContext(), getString(R.string.page_not_found).concat(" (http: " + errorResponse.getStatusCode() + ")") , Toast.LENGTH_LONG).show();
                        trueError = true;
                    }
                }

                @Override
                public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error){
                    if (request.getUrl().toString().contains(domainURL)) {
                        Toast.makeText(getApplicationContext(), getString(R.string.not_connected).concat(" (err: " + error + ")"), Toast.LENGTH_LONG).show();
                        trueError = true;
                    }
                    super.onReceivedError(view, request, error);
                }

                @Override
                @SuppressWarnings("deprecation")
                public void onReceivedError(WebView view, int errorCode, String description, String failingUrl){
                    if (failingUrl.contains(domainURL)) {
                        Toast.makeText(getApplicationContext(), getString(R.string.failed_loading), Toast.LENGTH_SHORT).show();
                        trueError = true;
                    }
                    super.onReceivedError(view, errorCode, description, failingUrl);
                }
            });

            browser.loadUrl(urlToLoad);

        } else {
            HideLoading();
        }

        HideHomeButton();
    }

    private void ShowBasket(final Context ctx) {
        this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                text_cartCount = (TextView) findViewById(R.id.text_cartCount);
                if (basketTotal > 0) {
                    if (basketTotal > 9) {
                        text_cartCount.setText(basketTotal);
                    } else {
                        text_cartCount.setText("0" + basketTotal);
                    }
                    menu.findItem(R.id.action_basket).setIcon(ContextCompat.getDrawable(ctx, R.drawable.icon_basket_items));
                } else {
                    text_cartCount.setText("");
                    menu.findItem(R.id.action_basket).setIcon(ContextCompat.getDrawable(ctx, R.drawable.icon_basket_empty));
                }
            }
        });
    }

    private void ShowHomeButton() {
        HomeButton.setEnabled(true);
        HomeButton.setClickable(true);
        HomeButton.setAlpha(1f);
    }

    private void HideHomeButton() {
        HomeButton.setEnabled(false);
        HomeButton.setClickable(false);
        HomeButton.setAlpha(0f);
    }

    private void ApplyScrollListener() {

        browser.setOnScrollChangeListener(new View.OnScrollChangeListener() {

            @Override
            public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {

                if (scrollY > oldScrollY) {
                    UPCount++;
                    DOWNCount = 0;
                }
                if (scrollY < oldScrollY) {
                    DOWNCount++;
                    UPCount = 0;
                }

                if (UPCount > 0) {
                    HideHomeButton();
                }
                if (DOWNCount > 10) {
                    ShowHomeButton();
                }
            }
        });
    }

    private void LoadClicksHandlers() {

        domainURL = getString(R.string.domainURL);

        // find the WebView by name in the main.xml of step 2
        browser = (WebView)findViewById(R.id.browser);

        progrbar = (ProgressBar)findViewById(R.id.progressBar);
        menuLabel = (TextView) findViewById(R.id.toolbar_title);
        HomeButton = (FloatingActionButton)findViewById(R.id.HomeButton);
        List_Categories = (NestedScrollView)findViewById(R.id.List_Categories);

        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.category_recyclerView);
        recyclerView .setNestedScrollingEnabled(false);

        ImageView img_fotos = (ImageView) findViewById(R.id.img_fotos);
        img_fotos.setImageResource(R.drawable.teaser_fotos);

        ImageView img_fotobook = (ImageView) findViewById(R.id.img_fotobook);
        img_fotobook.setImageResource(R.drawable.teaser_fotobook);

        ImageView img_fotocalendar = (ImageView) findViewById(R.id.img_fotocalendar);
        img_fotocalendar.setImageResource(R.drawable.teaser_fotocalendar);

        ImageView img_wallpicture = (ImageView) findViewById(R.id.img_wallpicture);
        img_wallpicture.setImageResource(R.drawable.teaser_wallpictures);

        ImageView img_fotogift = (ImageView) findViewById(R.id.img_fotogift);
        img_fotogift.setImageResource(R.drawable.teaser_fotogift);

        ImageView img_photocards = (ImageView) findViewById(R.id.img_photocards);
        img_photocards.setImageResource(R.drawable.teaser_photocard);

        img_fotos.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                LoadURL(getString(R.string.url_fotos));

            }
        });

        img_fotobook.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                LoadURL(getString(R.string.url_fotobook));
            }
        });

        img_fotocalendar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                LoadURL(getString(R.string.url_fotocalendar));
            }
        });

        img_wallpicture.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                LoadURL(getString(R.string.url_wallpicture));
            }
        });

        img_fotogift.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                LoadURL(getString(R.string.url_fotogift));
            }
        });

        img_photocards.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                LoadURL(getString(R.string.url_photocards));
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent){
        super.onActivityResult(requestCode, resultCode, intent);
        if(Build.VERSION.SDK_INT >= 21){
            Uri[] results = null;
            //Check if response is positive
            if(resultCode== Activity.RESULT_OK){
                if(requestCode == FCR){
                    if(null == mUMA){
                        return;
                    }
                    if(intent == null || intent.getData() == null){
                        //Capture Photo if no image available
                        if(mCM != null){
                            results = new Uri[]{Uri.parse(mCM)};
                        }
                    }else{
                        String dataString = intent.getDataString();
                        if(dataString != null){
                            results = new Uri[]{Uri.parse(dataString)};
                        }
                    }
                }
            }
            mUMA.onReceiveValue(results);
            mUMA = null;
        }else{
            if(requestCode == FCR){
                if(null == mUM) return;
                Uri result = intent == null || resultCode != RESULT_OK ? null : intent.getData();
                mUM.onReceiveValue(result);
                mUM = null;
            }
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED || ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA}, 1);
        }

        LoadClicksHandlers();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setTitle("");

        HomeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShowCatalog();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        FirstLoadURL();
    }

    private void FirstLoadURL() {
        firstLoad = true;
        LoadURL(getString(R.string.url_basket));
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            if(browser.canGoBack() && browser.getVisibility() == View.VISIBLE){

                WebBackForwardList mWebBackForwardList = browser.copyBackForwardList();
                if (mWebBackForwardList.getCurrentIndex() > 0) {
                    String historyUrl = mWebBackForwardList.getItemAtIndex(mWebBackForwardList.getCurrentIndex()-1).getUrl();
                    LoadTitle(historyUrl);
                }

                browser.goBack();
            }else{

                if(List_Categories.getVisibility() != View.VISIBLE){
                    ShowCatalog();
                }
                else {

                    new AlertDialog.Builder(this)
                            .setIcon(android.R.drawable.ic_dialog_alert)
                            .setTitle(R.string.sure_close_app_title)
                            .setMessage(R.string.sure_close_app_question)
                            .setPositiveButton(R.string.sure_close_app_yes, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    finish();
                                }

                            })
                            .setNegativeButton(R.string.sure_close_app_no, null)
                            .show();
                }
            }
            //super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        this.menu = menu;
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_basket) {
            LoadURL(getString(R.string.url_basket));
            return true;
        }


        return super.onOptionsItemSelected(item);
    }

    @Override
    @SuppressWarnings("StatementWithEmptyBody")
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.menu_product_overview) {
            ShowCatalog();
        } else if (id == R.id.menu_help_contact) {
             LoadURL(getString(R.string.url_help_contact));
        } else if (id == R.id.menu_order_status) {
            LoadURL(getString(R.string.url_order_status));
        } else if (id == R.id.menu_formats_prices) {
            LoadURL(getString(R.string.url_formats_prices));
        } else if (id == R.id.menu_shipping) {
            LoadURL(getString(R.string.url_shipping));
        } else if (id == R.id.menu_secure_payment) {
            LoadURL(getString(R.string.url_secure_payment));
        } else if (id == R.id.menu_logIn) {
            LoadURL(getString(R.string.url_logIn));
        } else if (id == R.id.menu_terms_conditions) {
            LoadURL(getString(R.string.url_terms_conditions));
        } else if (id == R.id.menu_data_protection) {
            LoadURL(getString(R.string.url_data_protection));
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}