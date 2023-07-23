package tn1.popo.Hopfully;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Filter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class AdapterFunctions extends ArrayAdapter<String> {
    private List<String> funcfull;


    public AdapterFunctions(@NonNull Context context, @NonNull List<String> func) {
        super(context, 0, func);
        funcfull = new ArrayList<String>(func);
    }

    @NonNull
    @Override
    public Filter getFilter() {
        return funcFilter;
    }
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(
                    R.layout.dropdown, parent, false
            );
        }

        TextView textViewName = convertView.findViewById(R.id.text_view_name);


        String f = getItem(position);

        if (f != null) {
            textViewName.setText(f);
        }

        return convertView;
    }



    private Filter funcFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults results = new FilterResults();
            List<String> suggestions = new ArrayList<>();

            if (constraint == null || constraint.length() == 0) {
                suggestions.addAll(funcfull);
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim().substring(constraint.length()-1);

                for (String item : funcfull) {
                    if (item.toLowerCase().contains(filterPattern)) {
                        suggestions.add(item);
                    }
                }
            }

            results.values = suggestions;
            results.count = suggestions.size();

            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            clear();
            addAll((List) results.values);
            notifyDataSetChanged();
        }

        @Override
        public CharSequence convertResultToString(Object resultValue) {
            return ((String) resultValue);
        }
    };

}
