import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ListenerRegistration
import com.grtc.gdibpm.management.area.Area

class AreaViewModel : ViewModel() {
    private val firestore: FirebaseFirestore = FirebaseFirestore.getInstance()
    val areaListMutable = MutableLiveData<MutableList<Area>>()
    private var listenerRegistration: ListenerRegistration? = null

    init {
        listenToAreaChanges()
    }

    fun listenToAreaChanges() {
        listenerRegistration = firestore.collection("areas")
            .addSnapshotListener { snapshots, error ->
                if (error != null) {
                    areaListMutable.postValue(mutableListOf())
                    return@addSnapshotListener
                }

                val listArea = mutableListOf<Area>()
                if (snapshots != null) {
                    for (document in snapshots) {
                        val id = document.id
                        val data = document.data
                        val name = data["name"] as String
                        val area = Area(id, name)
                        listArea.add(area)
                    }
                }
                areaListMutable.postValue(listArea)
            }
    }

    fun registerArea(area: Area) {
        val areaMap = hashMapOf(
            "name" to area.name
        )
        firestore.collection("areas")
            .add(areaMap)
    }

    override fun onCleared() {
        super.onCleared()
        listenerRegistration?.remove()
    }

    fun deleteArea(area: Area) {
        // Eliminar el área de Firestore
        firestore.collection("areas")
            .document(area.id)
            .delete()
            .addOnSuccessListener {
                // Opcional: Manejar éxito si es necesario
            }
            .addOnFailureListener { e ->
                // Opcional: Manejar error si es necesario
            }
    }
}
