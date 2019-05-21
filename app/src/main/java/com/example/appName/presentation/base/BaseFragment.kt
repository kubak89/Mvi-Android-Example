package com.example.appName.presentation.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import com.example.appName.common.extension.goToMainScreen
import com.example.appName.common.extension.goToRegisterActivity
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.AndroidSupportInjection
import dagger.android.support.HasSupportFragmentInjector
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import java.io.Serializable
import javax.inject.Inject

abstract class BaseFragment<VIEW_STATE : Serializable, PRESENTER : BasePresenter<VIEW_STATE, *>>(
        @LayoutRes val layoutId: Int
) : Fragment(), HasSupportFragmentInjector, BaseView {

    @Inject
    lateinit var presenter: PRESENTER

    @Inject
    lateinit var childFragmentInjector: DispatchingAndroidInjector<Fragment>

    var savedInstanceState: Bundle? = null

    private var disposable: Disposable? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(layoutId, null, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        this.savedInstanceState = savedInstanceState
        AndroidSupportInjection.inject(this)
        super.onViewCreated(view, savedInstanceState)
        subscribeToViewState()
        bind()
    }

    override fun onDestroy() {
        super.onDestroy()

        disposable?.dispose()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        outState.putSerializable(KEY_SAVED_FRAGMENT_VIEW_STATE, presenter.currentViewState)
    }

    override fun supportFragmentInjector(): AndroidInjector<Fragment> {
        return childFragmentInjector
    }

    override fun finishCurrentFeature() {
        requireActivity().onBackPressed()
    }

    override fun goToMainFeature() {
        if (activity is BaseActivity<*, *>) {
            requireActivity().goToMainScreen()
        }
    }

    override fun goToRegisterFeature() {
        if (activity is BaseActivity<*, *>) {
            requireActivity().goToRegisterActivity()
        }
    }

    protected fun subscribeToViewState() {
        disposable = presenter.stateObservable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(this::render)
    }

    open fun bind() {}

    abstract fun render(viewState: VIEW_STATE)

    companion object {
        const val KEY_SAVED_FRAGMENT_VIEW_STATE = "viewState"
    }
}