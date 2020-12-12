package insan.app.insanparty.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import insan.app.insanparty.R;
import insan.app.insanparty.UtilFunctions;
import insan.app.insanparty.model.memberlist.MemberList;

public class AdapterMemberList extends RecyclerView.Adapter<AdapterMemberList.ViewHolder> {

    private ArrayList<MemberList> data;
    private Context context;
    OnClickLitener litener;

    public AdapterMemberList(Context context, ArrayList<MemberList> data) {
        this.data = data;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_member,null);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        MemberList memberList = data.get(position);

        holder.nameTxt.setText(memberList.getName());

        Picasso.with(holder.profileImg.getContext())
                .load(UtilFunctions.urlUser + memberList.getImage())
                .into(holder.profileImg);

        holder.categoryCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                litener.onClick(position, memberList);
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView profileImg;
        TextView nameTxt;
        LinearLayout categoryCard;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            profileImg = itemView.findViewById(R.id.profileImg);
            categoryCard = itemView.findViewById(R.id.categoryCard);
            nameTxt = itemView.findViewById(R.id.nameTxt);
        }
    }

    public interface OnClickLitener{
        public void onClick(int position, MemberList courses);
    }

    public void setOnClickListener(OnClickLitener listener){
        this.litener=listener;

    }
}
