package com.mtek.kidsvid.di


import com.example.corotinestest.ui.viewmodel.DetailsViewModel
import com.example.corotinestest.ui.viewmodel.HomePageViewModel
import com.example.corotinestest.ui.viewmodel.LastScreenViewModel
import com.example.corotinestest.ui.viewmodel.UserViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel {
        UserViewModel(get())
    }

    viewModel {
        DetailsViewModel(get())
    }

    viewModel {
        LastScreenViewModel(get())
    }

    viewModel {
        HomePageViewModel(get())
    }
//    viewModel {
//        RegisterViewModel(get())
//    }
//
//    viewModel {
//        InterestsViewModel(get())
//    }
//    viewModel {
//        ChildCreateViewModel(get())
//    }
//    viewModel {
//        ParentMyKidsViewModel(get())
//    }
//    viewModel {
//        ChildVideoViewModel(get())
//    }
//    viewModel {
//        UploadViewModel(get())
//    }
//    viewModel {
//        FormAboutViewModel(get())
//    }
// viewModel {
//        CategoriesViewModel(get())
//    }


}