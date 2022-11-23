package br.com.project.ecommerce;

import android.annotation.SuppressLint;
import android.content.Context;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import br.com.project.ecommerce.dtos.ProductDTO;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder>{
    Context context;
    List<ProductDTO> productDTOList;

    public ProductAdapter(Context context, List<ProductDTO> productDTOList) {
        this.context = context;
        this.productDTOList = productDTOList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_layoult, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        if (productDTOList != null && productDTOList.size() > 0){
            ProductDTO productDTO = productDTOList.get(position);
            holder.txtProduto_tv.setText(productDTO.getName());
            holder.txtEstoque_tv.setText(productDTO.getStock().toString());
            holder.txtValor_tv.setText(productDTO.getPrice().toString());

            holder.txtProduto_tv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // position
                    Log.d("TAG", "onClick: ".concat(productDTOList.get(position).toString()));
                }
            });

        } else {
            return;
        }
    }

    @Override
    public int getItemCount() {
        return productDTOList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView  txtProduto_tv, txtEstoque_tv, txtValor_tv;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            txtProduto_tv = itemView.findViewById(R.id.txtProduto_tv);
            txtEstoque_tv = itemView.findViewById(R.id.txtEstoque_tv);
            txtValor_tv = itemView.findViewById(R.id.txtValor_tv);
        }
    }

}
