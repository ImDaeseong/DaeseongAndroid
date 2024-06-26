package com.daeseong.textviewscroll_text;

import java.util.ArrayList;
import java.util.List;

public class urlApi {

    private static urlApi mApi = null;

    private List<urlItem> Items;

    public urlApi() {
        Items = new ArrayList<>();
    }

    public static urlApi getInstance() {
        if (mApi == null) {
            mApi = new urlApi();
        }
        return mApi;
    }

    public void setItem(String text, String url) {
        urlItem item = new urlItem();
        item.setItem(text, url);
        Items.add(item);
    }

    public urlItem getItem(int nItem) {
        if (nItem >= 0 && nItem < Items.size()) {
            return Items.get(nItem);
        }
        return null;
    }

    public List<urlItem> getItem() {
        return Items;
    }

    public void clear() {
        Items.clear();
    }

    public class urlItem {
        private String text;
        private String url;

        public void setItem(String text, String url) {
            this.text = text;
            this.url = url;
        }

        public String getText() {
            return text;
        }

        public String getUrl() {
            return url;
        }
    }

}
