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
				// TODO �Զ����ɵķ������
				
			}

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				// TODO �Զ����ɵķ������
				
			}

			@Override
			public void afterTextChanged(Editable s) {
				// TODO �Զ����ɵķ������
				/**�����ı���ı�֮�� ��ִ�еĶ���
				 * ��Ϊ����Ҫ���ľ��ǣ����ı���ı��ͬʱ�����ǵ�listview������Ҳ������Ӧ�ı䶯��������һ����ʾ�ڽ����ϡ�
				 * �����������Ǿ���Ҫ�������ݵ��޸ĵĶ����ˡ�
				 */
				if(s.length()==0){
					deleteImageView.setVisibility(View.GONE);//���ı���Ϊ��ʱ��������ʧ
				}else{
					deleteImageView.setVisibility(View.VISIBLE);//���ı���Ϊ��ʱ�����ֲ��
				}
				handler.post(eChanged);
			}
			
		});
		
		//
		deleteImageView.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO �Զ����ɵķ������
				searchEditText.setText("");
			}
		});
		
		//����ListView��Adapter
		getmData(mData);
		adapter=new SimpleAdapter(this,mData,android.R.layout.simple_list_item_2,
				new String[]{"title","text"},new int[]{android.R.id.text1,android.R.id.text2});
		mListView.setAdapter(adapter);
	}

	Runnable eChanged=new Runnable(){

		@Override
		public void run() {
			// TODO �Զ����ɵķ������
			String data=searchEditText.getText().toString();
			mData.clear();	//�����
			getmDataSub(mData,data);//��ȡ��������
			adapter.notifyDataSetChanged();//֪ͨ����
		}
		
	};
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	/**
	 * ��ø��������������data����Ԫ����ɸѡ��ɸѡ���������ݷ���mDataSubs��
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
     * ���Ԫ���� ����ʼ��mDatas
     * @param mDatas
     */
	
	private void getmData(ArrayList<Map<String,Object>> mDatas){
		Map<String ,Object> item=new HashMap<String,Object>();
		mListTitle.add("����һ������");
		mListText.add("�����ı�.\n 2014.09.18.19.50");
		
		item.put("title", mListTitle.get(0));
		item.put("text", mListText.get(0));
		mDatas.add(item);
		
		mListTitle.add("������һ������");
		mListText.add("������һ���ı�.\n2014.09.18.19.51");
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
