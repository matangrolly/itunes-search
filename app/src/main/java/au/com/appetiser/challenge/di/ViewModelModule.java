package au.com.appetiser.challenge.di;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import au.com.appetiser.challenge.ui.details.DetailsViewModel;
import au.com.appetiser.challenge.ui.main.MainViewModel;
import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class ViewModelModule {

  @Binds
  abstract ViewModelProvider.Factory bindViewModelFactory(ViewModelFactory factory);

  @Binds
  @IntoMap
  @ViewModelKey(MainViewModel.class)
  abstract ViewModel bindMainViewModel(MainViewModel viewModel);

  @Binds
  @IntoMap
  @ViewModelKey(DetailsViewModel.class)
  abstract ViewModel bindDetailsViewModel(DetailsViewModel viewModel);

}