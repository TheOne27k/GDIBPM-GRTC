import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.grtc.gdibpm.R
import com.grtc.gdibpm.management.area.AreaActivity
import com.grtc.gdibpm.management.area.AreaAdapter

class AreaFragment : Fragment() {
    private lateinit var areaViewModel: AreaViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_list_area, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        areaViewModel = ViewModelProvider(this)[AreaViewModel::class.java]
        val recyclerArea = view.findViewById<RecyclerView>(R.id.recyclerArea)
        val adapter = AreaAdapter()
        recyclerArea.adapter = adapter
        recyclerArea.layoutManager = LinearLayoutManager(activity)
        areaViewModel.areaListMutable.observe(viewLifecycleOwner, Observer { areas ->
            adapter.setArea(areas)
        })

        val btnAddArea = view.findViewById<Button>(R.id.btnAddArea)
        btnAddArea.setOnClickListener {
            val intent = Intent(activity, AreaActivity::class.java)
            startActivity(intent)
        }
    }

    companion object {
        fun newInstance(): AreaFragment = AreaFragment()
    }
}
