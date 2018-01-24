package squareandcube.com.candidateregister;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

class ListOfUsersAdapter extends RecyclerView.Adapter<ListOfUsersAdapter.UserViewHolder> {

    private List<CandidateDataModel> listUsers;
    private Context context;

    public ListOfUsersAdapter(List<CandidateDataModel> listUsers,Context context) {
        this.listUsers = listUsers;
        this.context = context;
    }

    @Override
    public UserViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_of_users, parent, false);
        return new UserViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final UserViewHolder holder, int position) {
        holder.textViewFirstName.setText(listUsers.get(position).getFirstName());
        holder.textViewLastName.setText(listUsers.get(position).getLastName());
        holder.textViewEmail.setText(listUsers.get(position).getEmailId());
        holder.textViewAvg.setText(listUsers.get(position).getAvg());
        holder.textViewPhoneNumber.setText(listUsers.get(position).getMobileNumber());

        holder.mUserCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mUserIntent = new Intent(v.getContext(),UserDetailsActivity.class);
                v.getContext().startActivity(mUserIntent);
                SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
                sharedPreferences.edit().putString("Email",holder.textViewEmail.getText().toString().trim()).apply();
            }
        });
    }
    @Override
    public int getItemCount() {
        Log.v(ListOfUsersAdapter.class.getSimpleName(),""+listUsers.size());
        return listUsers.size();
    }



    public class UserViewHolder extends RecyclerView.ViewHolder {

        public TextView textViewFirstName;
        public TextView textViewLastName;
        public TextView textViewEmail;
        public TextView textViewAvg;
        public TextView textViewPhoneNumber;

        public CardView mUserCardView;

        public UserViewHolder(View view) {
            super(view);
            textViewFirstName = (TextView) view.findViewById(R.id.textViewFirstName);
            textViewLastName = (TextView) view.findViewById(R.id.textViewLastName);
            textViewEmail = (TextView) view.findViewById(R.id.textViewEmail);
            textViewAvg = (TextView) view.findViewById(R.id.textViewAvg);
            textViewPhoneNumber = (TextView)view.findViewById(R.id.textViewPhoneNumber);

            mUserCardView = (CardView) view.findViewById(R.id.user_cardView);
        }
    }
}
