package com.camoutech.projetfragment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.FragmentManager
import com.camoutech.projetfragment.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), PersonFragment.CallBackListener {
    private lateinit var binding: ActivityMainBinding
    private var familyAccount: Double = 300.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.availableText.text = "Argent disponible: $familyAccount$"

        //1. Fragment Manager
        val manager: FragmentManager = supportFragmentManager

        //2. Instancie le fragment
        val fragment1 = PersonFragment.newInstance("", 0.0)
        val fragment2 = PersonFragment.newInstance("", 0.0)

        //3. Remplacer le Frame par le fragment
        val transaction = manager.beginTransaction()
        transaction.replace(R.id.fragment_1, fragment1)
        transaction.replace(R.id.fragment_2, fragment2)
        // 4. Ajouter au BackStack
        transaction.addToBackStack(null)
        //5. Commit
        transaction.commit()
        manager.setFragmentResultListener(
            "Add money",
            this
        ){
            requestKey, bundle ->
            println("Key: $requestKey et bundle: $bundle")
        }
    }

    override fun onButtonClick(): Boolean {
        if (familyAccount < 5.0) return false
        familyAccount -= 5
        binding.availableText.text = "Argent disponible: $familyAccount$"
        return true
    }
}