package com.example.taskmanager.ui.profile
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import com.example.taskmanager.data.local.Pref
import com.example.taskmanager.databinding.FragmentProfileBinding

class ProfileFragment : Fragment() {

    private val pref: Pref by lazy {
        Pref(requireContext())
    }

    private lateinit var binding: FragmentProfileBinding

    private val getContent = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        result.data?.data?.let { uri ->
            setImage(uri)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.editText.setText(pref.getName())
        binding.editText.addTextChangedListener {
            pref.saveName(binding.editText.text.toString())
        }
        binding.profileImage.setOnClickListener {
            openTheGallery()
        }
    }

    private fun openTheGallery() {
        val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        getContent.launch(intent)
    }

    private fun setImage(uri: Uri) {
        binding.profileImage.setImageURI(uri)
    }
}