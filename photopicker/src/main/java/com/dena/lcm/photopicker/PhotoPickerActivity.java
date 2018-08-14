package com.dena.lcm.photopicker;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class PhotoPickerActivity extends Activity implements ImageDataSource.OnImagesLoadedListener {

    private LCMResource R;
    private GridView mGridView;
    private TextView mGallery;
    private ImageView mBackIV;
    private View mToolbar;
    private PhotoAdapter mPhotoAdapter;
    private ImageFolderAdapter mImageFolderAdapter;
    private List<ImageItem> mImageDataList = new ArrayList<>();
    private List<ImageFolder> mImageDataFolders = new ArrayList<>();
    private FolderPopupWindow mFolderPopupWindow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        R = LCMResource.getInstance(this);
        setContentView(R.getLayoutForId("activity_lcm_photo_picker"));

        initView();
        initAdapter();
        initData();

        setListeners();
    }

    private void setListeners() {
        mGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent();
                intent.putExtra("imagePath", mImageDataList.get((int) id).path);
                setResult(0, intent);
                finish();
            }
        });
    }

    private void initAdapter() {
        mPhotoAdapter = new PhotoAdapter(mImageDataList, this);
        mGridView.setAdapter(mPhotoAdapter);
        mImageFolderAdapter = new ImageFolderAdapter(this, mImageDataFolders);
    }

    private void initData() {
        new ImageDataSource(this, null, this);
    }

    private void initView() {
        mGridView = (GridView) findViewById(R.getId("picker_grid_view"));
        mGallery = (TextView) findViewById(R.getId("picker_open_gallery"));
        mBackIV = (ImageView) findViewById(R.getId("picker_back"));
        mToolbar = findViewById(R.getId("cs_image_picker_toolbar"));
        mBackIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        mGallery.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick(View v) {
                createPopupFolderList();
            }
        });
    }


    @Override
    public void onImagesLoaded(List<ImageFolder> imageFolders) {
        LCMLog.e("PicklerActivity", "imageFolders:: size=" + imageFolders.size());
        if (imageFolders != null) {
            mImageDataFolders.clear();
            mImageDataList.clear();
            mImageDataFolders.addAll(imageFolders);
            for (int i = 0; i < imageFolders.size(); i++) {
                mImageDataList.addAll(imageFolders.get(i).images);
            }
            mPhotoAdapter.refreshData(mImageDataList);
        }
    }


    /**
     * 创建弹出的ListView
     */
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private void createPopupFolderList() {
        mFolderPopupWindow = new FolderPopupWindow(this, mImageFolderAdapter);
        mImageFolderAdapter.refreshData(mImageDataFolders);
        mFolderPopupWindow.setAnimationStyle(R.getStyle("CsImagePopupAnim"));

        mFolderPopupWindow.setOnItemClickListener(new FolderPopupWindow.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                mImageFolderAdapter.setSelectIndex(position);
                mFolderPopupWindow.dismiss();
                ImageFolder imageFolder = (ImageFolder) adapterView.getAdapter().getItem(position);
                if (null != imageFolder) {
                    mImageDataList.clear();
                    mImageDataList.addAll(imageFolder.images);
                    mPhotoAdapter.refreshData(mImageDataList);
                }
            }
        });
        mFolderPopupWindow.showAtLocation(getWindow().getDecorView());
    }


}
