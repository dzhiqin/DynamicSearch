package com.example.dynamicsearch;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class MainActivity extends Activity {

	private EditText searchEditText;
	private ImageView deleteImageView;
	private ListView mListView;
	ArrayList<Map<String , Object>> mData=new ArrayList<Map<String ,Object>>();
	ArrayList<String>  mListTitle=new ArrayList<String>();
	ArrayList<String>mListText=new ArrayList<String>();
	SimpleAdapter adapter;
	Handler handler=new Handler();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fragment2_main);
		searchEditText=(EditText)findViewById(R.id.etSearch);
		deleteImageView=(ImageView)findViewById(R.id.ivDeleteText);
		mListView=(ListView)findViewById(R.id.mListView);
		//
		searchEditText.addTextChangedListener(new TextWatcher(){

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// TODO 自动生成的方法存根
				
			}

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				// TODO 自动生成的方法存根
				
			}

			@Override
			public void afterTextChanged(Editable s) {
				// TODO 自动生成的方法存根
				/**这是文本框改变之后 会执行的动作
				 * 因为我们要做的就是，在文本框改变的同时，我们的listview的数据也进行相应的变动，并且如一的显示在界面上。
				 * 所以这里我们就需要加上数据的修改的动作了。
				 */
				if(s.length()==0){
					deleteImageView.setVisibility(View.GONE);//当文本框为空时，则叉叉消失
				}else{
					deleteImageView.setVisibility(View.VISIBLE);//当文本框不为空时，出现叉叉
				}
				handler.post(eChanged);
			}
			
		});
		
		//
		deleteImageView.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO 自动生成的方法存根
				searchEditText.setText("");
			}
		});
		
		//设置ListView的Adapter
		getmData(mData);
		adapter=new SimpleAdapter(this,mData,android.R.layout.simple_list_item_2,
				new String[]{"title","text"},new int[]{android.R.id.text1,android.R.id.text2});
		mListView.setAdapter(adapter);
	}

	Runnable eChanged=new Runnable(){

		@Override
		public void run() {
			// TODO 自动生成的方法存根
			String data=searchEditText.getText().toString();
			mData.clear();	//先清空
			getmDataSub(mData,data);//获取更新数据
			adapter.notifyDataSetChanged();//通知更新
		}
		
	};
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	/**
	 * 获得根据搜索框的数据data来从元数据筛选，筛选出来的数据放入mDataSubs里
	 * @param mDataSubs
	 * @param data
	 */
	private void getmDataSub(ArrayList<Map<String ,Object>> mDataSubs,String data){
		int length=mListTitle.size();
		for(int i=0;i<length;i++){
			if(mListTitle.get(i).contains(data)||mListText.get(i).contains(data)){
				Map<String ,Object>item=new HashMap<String,Object>();
				item.put("title", mListTitle.get(i));
				item.put("text", mListText.get(i));
				mDataSubs.add(item);
			}
		}
	} 
	
	/**
     * 获得元数据 并初始化mDatas
     * @param mDatas
     */
	
	private void getmData(ArrayList<Map<String,Object>> mDatas){
		Map<String ,Object> item=new HashMap<String,Object>();
		mListTitle.add("这是一个标题");
		mListText.add("这是文本.\n 2014.09.18.19.50");
		
		item.put("title", mListTitle.get(0));
		item.put("text", mListText.get(0));
		mDatas.add(item);
		
		mListTitle.add("这是另一个标题");
		mListText.add("这是另一个文本.\n2014.09.18.19.51");
		item=new HashMap<String,Object>();
		item.put("title", mListTitle.get(1));
		item.put("text",mListTitle.get(1));
		mDatas.add(item);
	}
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
