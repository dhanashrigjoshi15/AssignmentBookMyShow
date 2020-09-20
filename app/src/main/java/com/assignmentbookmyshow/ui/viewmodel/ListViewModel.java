package com.assignmentbookmyshow.ui.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.assignmentbookmyshow.data.model.MovieData;
import com.assignmentbookmyshow.data.rest.ApiService;
import com.assignmentbookmyshow.data.rest.MovieRepository;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListViewModel extends ViewModel {
    private final MutableLiveData<ArrayList<MovieData>> repos = new MutableLiveData<>();
    private final MutableLiveData<Boolean> repoLoadError = new MutableLiveData<>();
    private final MutableLiveData<Boolean> loading = new MutableLiveData<>();
    ArrayList<MovieData> movies =  new ArrayList<>();

    public ListViewModel() {
        fetchRepos();
    }

    public MutableLiveData<ArrayList<MovieData>> getRepos() {
        return repos;
    }

    private void fetchRepos() {
        MovieRepository.getApi().getMovies("1","b74b5b9efd96596029e315e4d24ab3e4").enqueue(new Callback<List<MovieData>>() {
            @Override
            public void onResponse(Call<List<MovieData>> call, Response<List<MovieData>> response) {
                repos.setValue((ArrayList<MovieData>) response.body());
            }

            @Override
            public void onFailure(Call<List<MovieData>> call, Throwable t) {

            }
        });

       /* disposable.add(movieRepository.getMovies().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribeWith(new DisposableSingleObserver<List<MovieData>>() {
                    @Override
                    public void onSuccess(List<MovieData> value) {
                        repoLoadError.setValue(false);
                        repos.setValue(value);
                        loading.setValue(false);
                    }

                    @Override
                    public void onError(Throwable e) {
                        repoLoadError.setValue(true);
                        loading.setValue(false);
                    }
                }));*/
    }

    @Override
    protected void onCleared() {
        super.onCleared();
    }
}
