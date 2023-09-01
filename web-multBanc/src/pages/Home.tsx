import SearchBar from "../components/SearchBar";
import { Switch, Route, useHistory } from 'react-router-dom';
import SideBar from "../components/SideBar";
import TabPage from "../components/TabPage";

export default function Home() {
 
  const history = useHistory();

  const handleOptionClick = (option) => {
    history.push(`/${option}`);
  };
  return (
    <div className="flex">
    <SideBar onOptionClick={handleOptionClick} />
    <div className="flex-1">
      <SearchBar />
      <Switch>
        <Route path="/option1" component={PageOption1} />
        <Route path="/option2" component={PageOption2} />
        <Route path="/option3" component={PageOption3} />
        <Route path="/option4" component={PageOption4} />
        <Route path="/option5" component={PageOption5} />
      </Switch>
    </div>
  </div>
  );
}