package test.assignment.test_kwadran

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import test.assignment.test_kwadran.database.TableData

class AdapterDatabase(
    private val data: List<TableData>,
) : RecyclerView.Adapter<AdapterDatabase.ListViewHolder>() {

    inner class ListViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {

        private val tvNo: TextView = itemView.findViewById(R.id.tv_no)
        private val tvMax: TextView = itemView.findViewById(R.id.tv_max)
        private val tvUrutan: TextView = itemView.findViewById(R.id.tv_urutan)

        fun bindData(listItem: TableData) {

            tvNo.text = listItem.id.toString()
            tvMax.text = listItem.max.toString()
            tvUrutan.text = listItem.urutan.toString()
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_result_table, parent, false)
        return ListViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        holder.bindData(data[position])
    }

    override fun getItemCount(): Int = data.size

}
