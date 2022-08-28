package com.alwin.official

import android.os.Bundle
import android.view.View
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import com.alwin.android.R
import com.alwin.android.databinding.FragmentOfficialBinding
import com.alwin.util.binding
import com.alwin.util.dp2px

class OfficialFragment : Fragment(R.layout.fragment_official) {

    private val binding: FragmentOfficialBinding by binding()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        // super.onViewCreated(view, savedInstanceState)
        // val drawable = ResourcesCompat.getDrawable(resources, R.drawable.ic_vip_tip, null)!!
        // drawable.setBounds(0, 0, drawable.intrinsicWidth, drawable.intrinsicHeight)
        // binding.t1.compoundDrawablePadding = 2.dp2px
        // binding.t1.setCompoundDrawablesWithIntrinsicBounds(null, null, drawable, null)
        // binding.t1.text = "1212"
        // binding.t2.text = "sfdsffffffffffffffffffff"
        // binding.t3.text = "sfdsffffffffffffffsfdsffffffffffffffsfdsffffffffffffff"
    }
}