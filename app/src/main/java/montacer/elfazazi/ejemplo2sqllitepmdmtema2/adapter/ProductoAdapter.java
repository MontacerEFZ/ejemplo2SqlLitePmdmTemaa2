package montacer.elfazazi.ejemplo2sqllitepmdmtema2.adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import montacer.elfazazi.ejemplo2sqllitepmdmtema2.R;
import montacer.elfazazi.ejemplo2sqllitepmdmtema2.modelos.Producto;

public class ProductoAdapter extends RecyclerView.Adapter<ProductoAdapter.ProductoVH> {
    private Context context;
    private List<Producto> objects;
    private int resource;

    public ProductoAdapter(Context context, List<Producto> objects, int resource) {
        this.context = context;
        this.objects = objects;
        this.resource = resource;
    }
    @NonNull
    @Override
    public ProductoVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View productView = LayoutInflater.from(context).inflate(resource, null);

        productView.setLayoutParams(
                new RecyclerView.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT
                )
        );
        return new ProductoVH(productView);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductoVH holder, int position) {
        Producto producto = objects.get(position);

        holder.lbNombre.setText(producto.getNombre());
        holder.lbTotal.setText(String.valueOf(producto.getTotal()));

        holder.imgEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                confirmarEditar(holder.getAdapterPosition()).show();
            }
        });

        holder.imgDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // objects.remove(holder.getAdapterPosition()); forma 1 de borrar, pero no pregunta al usuario
                confirmarBorrar(holder.getAdapterPosition()).show();
            }
        });
    }

    private AlertDialog confirmarBorrar(int posicion){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Estas seguro?");
        builder.setCancelable(false);

        builder.setNegativeButton("Cancelar", null);
        builder.setPositiveButton("Borrar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                objects.remove(posicion);
                notifyItemRemoved(posicion);
            }
        });

        return builder.create();
    }

    private AlertDialog confirmarEditar(int posicion){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("modificar producto");
        builder.setCancelable(false);

        View productView = LayoutInflater.from(context).inflate(R.layout.product_view_model, null);
        EditText txtNombre = productView.findViewById(R.id.txtNombreProductViewModel);
        EditText txtCantidad = productView.findViewById(R.id.txtCantidadProductViewModel);
        EditText txtPrecio = productView.findViewById(R.id.txtPrecioProductViewModel);
        builder.setView(productView);

        Producto producto = objects.get(posicion);
        txtNombre.setText(producto.getNombre());
        txtCantidad.setText(String.valueOf(producto.getCantidad()));
        txtPrecio.setText(String.valueOf(producto.getPrecio()));

        builder.setNegativeButton("Cancelar", null);
        builder.setPositiveButton("Editar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (!txtNombre.getText().toString().isEmpty() && !txtCantidad.getText().toString().isEmpty()
                && !txtPrecio.getText().toString().isEmpty()){
                    objects.set(posicion, new Producto(txtNombre.getText().toString(),
                            Integer.parseInt(txtCantidad.getText().toString()),
                            Float.parseFloat(txtPrecio.getText().toString())));
                    notifyItemChanged(posicion);
                }
            }
        });
        return builder.create();
    }

    @Override

    public int getItemCount() {
        return objects.size();
    }

    public class ProductoVH extends RecyclerView.ViewHolder {

        TextView lbNombre, lbTotal;
        ImageButton imgEdit, imgDelete;

        public ProductoVH(@NonNull View itemView) {
            super(itemView);

            lbNombre = itemView.findViewById(R.id.lbNombreProductViewHolder);
            lbTotal = itemView.findViewById(R.id.lbTotalProductViewHolder);
            imgEdit = itemView.findViewById(R.id.imgEditProductViewHolder);
            imgDelete = itemView.findViewById(R.id.imgDeleteProductViewHolder);

        }
    }
}
