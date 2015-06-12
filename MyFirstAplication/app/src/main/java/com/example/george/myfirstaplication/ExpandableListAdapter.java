package com.example.george.myfirstaplication;

import android.content.Context;
import android.database.DataSetObserver;
import android.graphics.Typeface;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ExpandableListAdapter extends BaseExpandableListAdapter {
    private Context mContext;
    private ArrayList<String> groupItems;
    private HashMap<String, ArrayList<String>> childItems;

    public ExpandableListAdapter(Context context, ArrayList<String> groupItems, HashMap<String, ArrayList<String>> childItems){
        this.mContext = context;
        this.groupItems = groupItems;
        this.childItems = childItems;
    }

    @Override
    public void registerDataSetObserver(DataSetObserver observer) {

    }

    @Override
    public void unregisterDataSetObserver(DataSetObserver observer) {

    }

    @Override
    public int getGroupCount() {
        return this.groupItems.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return this.childItems.get(this.groupItems.get(groupPosition))
                .size();
    }

    @Override
    public Object getGroup(int groupPosition) {


        return this.groupItems.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return this.childItems.get(this.groupItems.get(groupPosition))
                .get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        String titlu = (String)getGroup(groupPosition);
        if(convertView==null){
            LayoutInflater inflater = (LayoutInflater)this.mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.header_layout,null);
        }
        TextView titles = (TextView)convertView.findViewById(R.id.groupItems);
        titles.setText(titlu);
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        final String childText = (String)getChild(groupPosition, childPosition);
        if(convertView==null){
            LayoutInflater inflater = (LayoutInflater)this.mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView=inflater.inflate(R.layout.item_layout,null);
        }
        TextView items = (TextView)convertView.findViewById(R.id.itemWithinGroups);
        items.setText(childText);

        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    @Override
    public boolean areAllItemsEnabled() {
        return false;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public void onGroupExpanded(int groupPosition) {

    }

    @Override
    public void onGroupCollapsed(int groupPosition) {

    }

    @Override
    public long getCombinedChildId(long groupId, long childId) {
        return 0;
    }

    @Override
    public long getCombinedGroupId(long groupId) {
        return 0;
    }
}
