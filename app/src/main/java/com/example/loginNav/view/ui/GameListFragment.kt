package com.example.loginNav.view.ui.gamelist

import android.os.Bundle
import android.view.*
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.loginNav.R
import com.example.loginNav.R.*
import com.example.loginNav.view.ui.DetailsFragment

import com.example.loginNav.model.GameList

var bundle: Bundle = Bundle()

/**
 *The GameList Fragement containing recyclerView
 *
 */
class GameListFragment : Fragment(),
    GamesAdapter.Onitemclicklistener,
    GamesAdapter.OnItemLongClicklistener {
    var gamelist: ArrayList<GameList> = ArrayList()
    private var mAdapter: GamesAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true);

    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val mLayoutManager: RecyclerView.LayoutManager = LinearLayoutManager(context)
        var view: View = inflater.inflate(layout.fragment_gamelist, container, false)
        var recyclerView: RecyclerView = view.findViewById(R.id.recycle)
        recyclerView.layoutManager = mLayoutManager
        mAdapter =
            GamesAdapter(
                gamelist,
                this,
                this
            )
        recyclerView.adapter = mAdapter //Passing value into the adapter
        prepareGameList()
        return view
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.options_menu, menu)
            super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.getItemId()) {
            R.id.settings -> {
                Toast.makeText(context, "Settings selected", Toast.LENGTH_LONG).show()
                //add new item here
                return true
            }
            R.id.logout -> {
                findNavController().navigate(R.id.action_gameListFragment_to_loginFragment)
                return true
            }
            R.id.exit -> {
                activity?.finishAffinity()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onLongClick(gameList: GameList) {

        val inflater = layoutInflater
        Toast.makeText(context, "Edit Your Text", Toast.LENGTH_SHORT).show()
        val dialogLayout = inflater.inflate(layout.editgametitle,null)
        val builder = view?.context?.let { AlertDialog.Builder(it) }
        val editTitleText :EditText= dialogLayout.findViewById(R.id.editTitleText)
        builder?.setView(dialogLayout)
        builder?.setTitle("Edit The Title")
        builder?.setPositiveButton("OK"
        ) { dialog, which ->
            gameList.title= editTitleText.text.toString()
            mAdapter?.notifyDataSetChanged()
            bundle.putString("title", editTitleText.text.toString())
            bundle.putString("imageurl", gameList.image)
            bundle.putString("genre", gameList.genre)
            bundle.putString("year", gameList.year)
            val detailsFragment = DetailsFragment()
            detailsFragment.arguments = bundle
        }
        builder?.setNegativeButton("Cancel",{dialog,which-> dialog.dismiss() })
        builder?.show()
    }

    override fun onClick(gameList: GameList) {
        Toast.makeText(context, gameList.title, Toast.LENGTH_LONG).show()
        bundle.putString("title", gameList.title)
        bundle.putString("imageurl", gameList.image)
        bundle.putString("genre", gameList.genre)
        bundle.putString("year", gameList.year)
        findNavController().navigate(R.id.action_gameListFragment_to_detailsFragment,
            bundle
        )
    }

    private fun prepareGameList() {
        var game =
            GameList(
                "https://upload.wikimedia.org/wikipedia/en/d/dc/Resident_Evil_3.jpg",
                "Resident Evil 3",
                "Action & Adventure",
                "2015"
            )
        gamelist.add(game)
        game = GameList(
            "https://upload.wikimedia.org/wikipedia/en/thumb/9/9d/Cover_Art_of_Doom_Eternal.png/220px-Cover_Art_of_Doom_Eternal.png",
            "Doom Eternal",
            "Animation, Kids & Family",
            "2015"
        )
        gamelist.add(game)
        game = GameList(
            "https://upload.wikimedia.org/wikipedia/en/c/ce/FFVIIRemake.png",
            "Cyberpunk 2077",
            "Action",
            "2015"
        )
        gamelist.add(game)
        game = GameList(
            "https://upload.wikimedia.org/wikipedia/en/1/1f/Animal_Crossing_New_Horizons.jpg",
            "SFinal Fantasy VII Remake",
            "Animation",
            "2015"
        )
        gamelist.add(game)
        game = GameList(
            "https://i.picsum.photos/id/1001/200/300.jpg",
            "Animal Crossing: New Horizons",
            "Science Fiction & Fantasy",
            "2015"
        )
        gamelist.add(game)
        game = GameList(
            "https://i.picsum.photos/id/1007/200/300.jpg",
            "Mission: Impossible Rogue Nation",
            "Action",
            "2015"
        )
        gamelist.add(game)
        game = GameList("https://i.picsum.photos/id/1008/200/300.jpg", "Up", "Animation", "2009")
        gamelist.add(game)
        game = GameList(
            "https://i.picsum.photos/id/1009/200/300.jpg",
            "Star Trek",
            "Science Fiction",
            "2009"
        )
        gamelist.add(game)
        game = GameList(
            "https://i.picsum.photos/id/1010/200/300.jpg",
            "The LEGO Movie",
            "Animation",
            "2014"
        )
        gamelist.add(game)
        game = GameList(
            "https://i.picsum.photos/id/1011/200/300.jpg",
            "Iron Man",
            "Action & Adventure",
            "2008"
        )
        gamelist.add(game)
        game = GameList(
            "https://i.picsum.photos/id/1012/200/300.jpg",
            "Aliens",
            "Science Fiction",
            "1986"
        )
        gamelist.add(game)
        game =
            GameList(
                "https://i.picsum.photos/id/1013/200/300.jpg",
                "Chicken Run",
                "Animation",
                "2000"
            )
        gamelist.add(game)
        game = GameList(
            "https://i.picsum.photos/id/1014/200/300.jpg",
            "Back to the Future",
            "Science Fiction",
            "1985"
        )
        gamelist.add(game)
        game =
            GameList(
                "https://i.picsum.photos/id/1015/200/300.jpg",
                "Raiders of the Lost Ark",
                "Action & Adventure",
                "1981"
            )
        gamelist.add(game)
        game = GameList(
            "https://i.picsum.photos/id/1016/200/300.jpg",
            "Goldfinger",
            "Action & Adventure",
            "1965"
        )
        gamelist.add(game)

        game = GameList(
            "https://i.picsum.photos/id/1001/200/300.jpg",
            "Guardians of the Galaxy",
            "Science Fiction & Fantasy",
            "2014"
        )
        gamelist.add(game)
        mAdapter?.notifyDataSetChanged()
    }

}
